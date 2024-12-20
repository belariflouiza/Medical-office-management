package com.example.appoitmentservice.Service;

import com.example.appoitmentservice.Model.Appointment;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    private final List<Appointment> appointmentDb = new ArrayList<>();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private Calendar googleCalendar;

    @Value("${google.calendar.credentials-path}")
    private String credentialsPath;

    @Value("${google.calendar.application-name}")
    private String applicationName;

    @PostConstruct
    public void init() {
        try {
            googleCalendar = initializeGoogleCalendar();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'initialisation de Google Calendar", e);
        }

        // Données fictives pour la base mémoire
        appointmentDb.add(new Appointment(1, 101, LocalDateTime.of(2024, 12, 20, 10, 0), "Consultation annuelle"));
        appointmentDb.add(new Appointment(2, 102, LocalDateTime.of(2024, 12, 21, 14, 0), "Suivi général"));
        appointmentDb.add(new Appointment(3, 103, LocalDateTime.of(2024, 12, 22, 9, 30), "Examen pré-opératoire"));
        appointmentDb.add(new Appointment(4, 104, LocalDateTime.of(2024, 12, 23, 15, 0), "Vaccination"));
    }

    private Calendar initializeGoogleCalendar() throws GeneralSecurityException, IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsPath))
                .createScoped(List.of(CalendarScopes.CALENDAR));
        return new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credentials)
        ).setApplicationName(applicationName).build();
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDb;
    }

    @Retryable(
            value = {IOException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    @HystrixCommand(fallbackMethod = "addAppointmentToLocalDB")
    public Appointment addAppointment(Appointment appointment) throws IOException {
        appointment.setId(appointmentDb.size() + 1);
        appointmentDb.add(appointment);
        addAppointmentToGoogleCalendar(appointment);
        return appointment;
    }

    private void addAppointmentToGoogleCalendar(Appointment appointment) throws IOException {
        Event event = new Event()
                .setSummary(appointment.getDescription())
                .setDescription("Rendez-vous avec le patient ID: " + appointment.getPatientId());

        Date start = Date.from(appointment.getAppointmentDateTime().atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(appointment.getAppointmentDateTime().plusHours(1).atZone(ZoneId.systemDefault()).toInstant());

        event.setStart(new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(start)));
        event.setEnd(new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(end)));

        googleCalendar.events().insert("primary", event).execute();
    }

    public Appointment addAppointmentToLocalDB(Appointment appointment) {
        System.out.println("Fallback: Sauvegarde uniquement en local.");
        return appointment;
    }

    @HystrixCommand(fallbackMethod = "updateAppointmentFallback")
    public Appointment updateAppointment(int id, Appointment updatedAppointment) {
        Appointment existingAppointment = getAppointmentById(id);

        existingAppointment.setPatientId(updatedAppointment.getPatientId());
        existingAppointment.setPractitionerId(updatedAppointment.getPractitionerId());
        existingAppointment.setAppointmentDateTime(updatedAppointment.getAppointmentDateTime());
        existingAppointment.setDescription(updatedAppointment.getDescription());

        return existingAppointment;
    }

    public Appointment updateAppointmentFallback(int id, Appointment updatedAppointment) {
        System.out.println("Fallback: Mise à jour locale uniquement.");
        return updatedAppointment;
    }

    @HystrixCommand(fallbackMethod = "deleteAppointmentFallback")
    public boolean deleteAppointment(int id) {
        return appointmentDb.removeIf(appointment -> appointment.getId() == id);
    }

    public boolean deleteAppointmentFallback(int id) {
        System.out.println("Fallback: Suppression locale uniquement.");
        return false;
    }

    public Appointment getAppointmentById(int id) {
        return appointmentDb.stream()
                .filter(appointment -> appointment.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable avec l'ID " + id));
    }
}

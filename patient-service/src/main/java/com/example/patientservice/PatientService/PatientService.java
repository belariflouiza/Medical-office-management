package com.example.patientservice.service;

import com.example.patientservice.PatientModel.Consultation;
import com.example.patientservice.PatientModel.Doctor;
import com.example.patientservice.PatientModel.Patient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private final List<Patient> patientDb = new ArrayList<>();
    private final List<Consultation> consultationDb = new ArrayList<>();

    public PatientService() {
        // Initialisation des médecins
        Doctor doctor1 = new Doctor(1L, "Dr. Smith", "Cardiologue");
        Doctor doctor2 = new Doctor(2L, "Dr. Brown", "Neurologue");

        // Initialisation des patients
        patientDb.add(new Patient(1L, "John Doe", LocalDate.of(1985, 5, 15), doctor1, List.of("Hypertension", "Asthme")));
        patientDb.add(new Patient(2L, "Jane Roe", LocalDate.of(1990, 8, 22), doctor2, List.of("Migraine")));
        patientDb.add(new Patient(3L, "Alice Green", LocalDate.of(1975, 3, 10), doctor1, List.of("Diabetes")));

        // Initialisation des consultations
        consultationDb.add(new Consultation(1L, patientDb.get(0), doctor1, LocalDateTime.of(2024, 1, 10, 14, 0), "Routine Checkup"));
        consultationDb.add(new Consultation(2L, patientDb.get(1), doctor2, LocalDateTime.of(2024, 1, 15, 10, 30), "Migraine Consultation"));
    }

    // Gestion des patients
    public List<Patient> getAllPatients() {
        return patientDb;
    }

    public Patient addPatient(Patient patient) {
        patient.setId((long) (patientDb.size() + 1));
        patientDb.add(patient);
        return patient;
    }

    public Patient getPatientById(Long id) {
        return patientDb.stream()
                .filter(patient -> patient.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = getPatientById(id);
        existingPatient.setName(updatedPatient.getName());
        existingPatient.setDob(updatedPatient.getDob());
        existingPatient.setPrimaryDoctor(updatedPatient.getPrimaryDoctor());
        existingPatient.setMedicalHistory(updatedPatient.getMedicalHistory());
        return existingPatient;
    }

    public void deletePatient(Long id) {
        if (!patientDb.removeIf(patient -> patient.getId().equals(id))) {
            throw new RuntimeException("Patient not found");
        }
    }

    public List<String> getMedicalHistoryByName(String name) {
        return patientDb.stream()
                .filter(patient -> patient.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(Patient::getMedicalHistory)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // Gestion des consultations
    public List<Consultation> getAllConsultations() {
        return consultationDb;
    }

    public Consultation addConsultation(Consultation consultation) {
        consultation.setId((long) (consultationDb.size() + 1));
        consultationDb.add(consultation);
        return consultation;
    }

    public Consultation getConsultationById(Long id) {
        return consultationDb.stream()
                .filter(consultation -> consultation.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Consultation not found"));
    }

    public Consultation updateConsultation(Long id, Consultation updatedConsultation) {
        Consultation existingConsultation = getConsultationById(id);
        existingConsultation.setPatient(updatedConsultation.getPatient());
        existingConsultation.setDoctor(updatedConsultation.getDoctor());
        existingConsultation.setConsultationDate(updatedConsultation.getConsultationDate());
        existingConsultation.setReason(updatedConsultation.getReason());
        return existingConsultation;
    }

    public void deleteConsultation(Long id) {
        if (!consultationDb.removeIf(consultation -> consultation.getId().equals(id))) {
            throw new RuntimeException("Consultation not found");
        }
    }
}

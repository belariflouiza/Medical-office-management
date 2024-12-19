package com.example.patientservice.Services;

import com.example.patientservice.Model.Patient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    // Base de données simulée en mémoire
    private final List<Patient> patientDb = new ArrayList<>();

    // Initialisation avec des données fictives
    public PatientService() {
        patientDb.add(new Patient("Alice Dupont", LocalDate.of(1990, 5, 15), "alice.dupont@example.com", "0612345678"));
        patientDb.add(new Patient("Bob Martin", LocalDate.of(1985, 8, 20), "bob.martin@example.com", "0623456789"));
        patientDb.add(new Patient("Charlie Dubois", LocalDate.of(1975, 2, 10), "charlie.dubois@example.com", "0634567890"));
    }

    // Récupérer tous les patients
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientDb); // Retourne une copie pour éviter les modifications externes
    }

    // Ajouter un patient
    public Patient addPatient(Patient patient) {
        patientDb.add(patient);
        return patient;
    }

    // Récupérer un patient par ID
    public Patient getPatientById(int id) {
        return patientDb.stream()
                .filter(patient -> patient.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Patient introuvable avec l'ID : " + id));
    }

    // Mettre à jour un patient existant
    public Patient updatePatient(int id, Patient updatedPatient) {
        Patient existingPatient = getPatientById(id);

        existingPatient.setName(updatedPatient.getName());
        existingPatient.setDateOfBirth(updatedPatient.getDateOfBirth());
        existingPatient.setEmail(updatedPatient.getEmail());
        existingPatient.setPhoneNumber(updatedPatient.getPhoneNumber());
        existingPatient.setMedicalHistory(updatedPatient.getMedicalHistory());

        return existingPatient;
    }

    // Supprimer un patient
    public void deletePatient(int id) {
        boolean removed = patientDb.removeIf(patient -> patient.getId() == id);
        if (!removed) {
            throw new RuntimeException("Patient introuvable avec l'ID : " + id);
        }
    }

    // Ajouter un antécédent médical pour un patient
    public void addMedicalHistory(int id, String record) {
        Patient patient = getPatientById(id);
        patient.addMedicalHistory(record);
    }

    // Récupérer l'historique médical d'un patient
    public List<String> getMedicalHistory(int id) {
        return getPatientById(id).getMedicalHistory();
    }
}

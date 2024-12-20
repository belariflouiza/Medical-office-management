package com.example.dossiermed.Services;

import com.example.dossiermed.Model.MedicalRecord;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService {

    private final List<MedicalRecord> medicalRecordsDb = new ArrayList<>();

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordsDb;
    }

    @Retryable(
            value = { Exception.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    @HystrixCommand(fallbackMethod = "createMedicalRecordFallback")
    public MedicalRecord createMedicalRecord(MedicalRecord record) {
        record.setId(medicalRecordsDb.size() + 1);
        medicalRecordsDb.add(record);
        synchronizeWithExternalServices(record);
        return record;
    }

    private void synchronizeWithExternalServices(MedicalRecord record) {
        // Simule la synchronisation avec les services Patient et Praticien.
        System.out.println("Synchronisation avec Patient Service et Practitioner Service...");
    }

    public MedicalRecord createMedicalRecordFallback(MedicalRecord record) {
        System.out.println("Fallback: Synchronisation échouée. Sauvegarde locale uniquement.");
        return record;
    }

    public MedicalRecord getMedicalRecordById(int id) {
        return medicalRecordsDb.stream()
                .filter(record -> record.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dossier médical introuvable avec l'ID : " + id));
    }

    public MedicalRecord updateMedicalRecord(int id, MedicalRecord updatedRecord) {
        MedicalRecord existingRecord = getMedicalRecordById(id);
        existingRecord.setContent(updatedRecord.getContent());
        return existingRecord;
    }

    public boolean deleteMedicalRecord(int id) {
        return medicalRecordsDb.removeIf(record -> record.getId() == id);
    }
}

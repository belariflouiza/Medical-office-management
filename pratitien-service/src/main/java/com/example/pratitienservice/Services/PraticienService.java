package com.example.pratitienservice.Services;




import com.example.pratitienservice.Model.Praticien;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PraticienService {

    private final List<Praticien> practitionerDb = new ArrayList<>();

    public PraticienService() {
        practitionerDb.add(new Praticien("Dr. Jean Dupont", "Cardiologue", "jean.dupont@example.com", "0611223344", 10));
        practitionerDb.add(new Praticien("Dr. Marie Curie", "Neurologue", "marie.curie@example.com", "0677889900", 15));
    }

    public List<Praticien> getAllPractitioners() {
        return practitionerDb;
    }

    public Praticien addPractitioner(Praticien practitioner) {
        practitionerDb.add(practitioner);
        return practitioner;
    }

    public Praticien getPractitionerById(int id) {
        return practitionerDb.stream()
                .filter(practitioner -> practitioner.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Praticien introuvable"));
    }

    public Praticien updatePractitioner(int id, Praticien updatedPractitioner) {
        Praticien existingPractitioner = getPractitionerById(id);
        existingPractitioner.setName(updatedPractitioner.getName());
        existingPractitioner.setSpecialization(updatedPractitioner.getSpecialization());
        existingPractitioner.setEmail(updatedPractitioner.getEmail());
        existingPractitioner.setPhoneNumber(updatedPractitioner.getPhoneNumber());
        existingPractitioner.setYearsOfExperience(updatedPractitioner.getYearsOfExperience());
        return existingPractitioner;
    }

    public void deletePractitioner(int id) {
        if (!practitionerDb.removeIf(practitioner -> practitioner.getId() == id)) {
            throw new RuntimeException("Praticien introuvable");
        }
    }
}


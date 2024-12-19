package com.example.patientservice.PatientModel;

import java.time.LocalDate;
import java.util.List;

// Patient.java
public class Patient {
    private Long id;
    private String name;
    private LocalDate dob;
    private Doctor primaryDoctor;
    private List<String> medicalHistory;

    public Patient(Long id, String name, LocalDate dob, Doctor primaryDoctor, List<String> medicalHistory) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.primaryDoctor = primaryDoctor;
        this.medicalHistory = medicalHistory;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Doctor getPrimaryDoctor() {
        return primaryDoctor;
    }

    public void setPrimaryDoctor(Doctor primaryDoctor) {
        this.primaryDoctor = primaryDoctor;
    }

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(List<String> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
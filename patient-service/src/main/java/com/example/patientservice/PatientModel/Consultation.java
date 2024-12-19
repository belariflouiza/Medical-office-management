package com.example.patientservice.PatientModel;


import java.time.LocalDateTime;

public class Consultation {
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime consultationDate;
    private String reason;

    public Consultation(Long id, Patient patient, Doctor doctor, LocalDateTime consultationDate, String reason) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.consultationDate = consultationDate;
        this.reason = reason;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDateTime consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

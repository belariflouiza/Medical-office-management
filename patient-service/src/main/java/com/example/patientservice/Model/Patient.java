package com.example.patientservice.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "Modèle représentant les informations d'un patient")
public class Patient {

    private static int idCounter = 0;

    @ApiModelProperty(notes = "ID unique du patient, généré automatiquement")
    private final int id;

    @ApiModelProperty(notes = "Nom complet du patient")
    private String name;

    @ApiModelProperty(notes = "Date de naissance du patient")
    private LocalDate dateOfBirth;

    @ApiModelProperty(notes = "Adresse email du patient")
    private String email;

    @ApiModelProperty(notes = "Numéro de téléphone du patient")
    private String phoneNumber;

    @ApiModelProperty(notes = "Liste des antécédents médicaux")
    private List<String> medicalHistory;

    public Patient(String name, LocalDate dateOfBirth, String email, String phoneNumber) {
        this.id = ++idCounter;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.medicalHistory = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    public void addMedicalHistory(String record) {
        this.medicalHistory.add(record);
    }

    public void setMedicalHistory(List<String> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", medicalHistory=" + medicalHistory +
                '}';
    }
}

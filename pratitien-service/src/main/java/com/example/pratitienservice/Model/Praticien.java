package com.example.pratitienservice.Model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

@ApiModel(description = "Modèle représentant les informations d'un praticien")
public class Praticien {

    private static int idCounter = 0;

    @Id
    @ApiModelProperty(notes = "L'identifiant unique du praticien, généré automatiquement")
    private final int id;

    @ApiModelProperty(notes = "Nom complet du praticien")
    private String name;

    @ApiModelProperty(notes = "Spécialisation médicale du praticien")
    private String specialization;

    @ApiModelProperty(notes = "Adresse email du praticien")
    private String email;

    @ApiModelProperty(notes = "Numéro de téléphone du praticien")
    private String phoneNumber;

    @ApiModelProperty(notes = "Années d'expérience du praticien")
    private int yearsOfExperience;

    public Praticien(String name, String specialization, String email, String phoneNumber, int yearsOfExperience) {
        this.id = ++idCounter;
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.yearsOfExperience = yearsOfExperience;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return "Praticien{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}


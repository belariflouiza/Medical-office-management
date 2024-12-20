package com.example.appoitmentservice.Model;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * Représentation d'un rendez-vous dans le système de gestion médicale.
 */
@ApiModel(description = "Détails complets concernant un rendez-vous médical")
public class Appointment {

    private static int idGenerator = 0;

    @Id
    @ApiModelProperty(notes = "Identifiant unique du rendez-vous, généré automatiquement par le système")
    private int id;

    @ApiModelProperty(notes = "Identifiant du patient lié à ce rendez-vous")
    private int patientId;

    @ApiModelProperty(notes = "Identifiant du praticien en charge de ce rendez-vous")
    private int practitionerId;

    @ApiModelProperty(notes = "Date et heure prévues pour le rendez-vous")
    private LocalDateTime appointmentDateTime;

    @ApiModelProperty(notes = "Description détaillée ou motif du rendez-vous")
    private String description;

    /**
     * Constructeur par défaut, initialisant un ID unique pour chaque rendez-vous.
     */


    /**
     * Constructeur paramétré pour créer un rendez-vous avec des détails complets.
     *
     * @param patientId        Identifiant du patient
     * @param practitionerId   Identifiant du praticien
     * @param appointmentDateTime Date et heure du rendez-vous
     * @param description      Description ou motif du rendez-vous
     */
    public Appointment(int patientId, int practitionerId, LocalDateTime appointmentDateTime, String description) {
        this.id = ++idGenerator;
        this.patientId = patientId;
        this.practitionerId = practitionerId;
        this.appointmentDateTime = appointmentDateTime;
        this.description = description;
    }

    // Getters et setters détaillés

    /**
     * Retourne l'identifiant unique du rendez-vous.
     *
     * @return L'identifiant du rendez-vous
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du rendez-vous (réservé pour les tests).
     *
     * @param id L'identifiant du rendez-vous
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne l'identifiant du patient lié.
     *
     * @return L'identifiant du patient
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Définit l'identifiant du patient lié.
     *
     * @param patientId L'identifiant du patient
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * Retourne l'identifiant du praticien lié.
     *
     * @return L'identifiant du praticien
     */
    public int getPractitionerId() {
        return practitionerId;
    }

    /**
     * Définit l'identifiant du praticien lié.
     *
     * @param practitionerId L'identifiant du praticien
     */
    public void setPractitionerId(int practitionerId) {
        this.practitionerId = practitionerId;
    }

    /**
     * Retourne la date et l'heure du rendez-vous.
     *
     * @return La date et l'heure du rendez-vous
     */
    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    /**
     * Définit la date et l'heure du rendez-vous.
     *
     * @param appointmentDateTime La date et l'heure du rendez-vous
     */
    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    /**
     * Retourne la description ou le motif du rendez-vous.
     *
     * @return La description ou le motif
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description ou le motif du rendez-vous.
     *
     * @param description La description ou le motif
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", practitionerId=" + practitionerId +
                ", appointmentDateTime=" + appointmentDateTime +
                ", description='" + description + '\'' +
                '}';
    }
}


package com.example.dossiermed.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Dossier médical partagé entre un patient et un praticien.")
public class MedicalRecord {

    @ApiModelProperty(notes = "ID unique du dossier médical.")
    private int id;

    @ApiModelProperty(notes = "ID du patient associé.")
    private int patientId;

    @ApiModelProperty(notes = "ID du praticien associé.")
    private int practitionerId;

    @ApiModelProperty(notes = "Contenu du dossier médical.")
    private String content;

    public MedicalRecord() {
    }

    public MedicalRecord(int id, int patientId, int practitionerId, String content) {
        this.id = id;
        this.patientId = patientId;
        this.practitionerId = practitionerId;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(int practitionerId) {
        this.practitionerId = practitionerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

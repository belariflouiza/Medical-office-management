package com.example.patientservice.PatientController;

import com.example.patientservice.PatientModel.Consultation;
import com.example.patientservice.PatientModel.Doctor;
import com.example.patientservice.PatientModel.Patient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Api(value = "Systeme de management medical", description = "Gestion des patients et consultations")
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final com.example.patientservice.service.PatientService patientService;

    public PatientController(com.example.patientservice.service.PatientService patientService) {
        this.patientService = patientService;
    }

    @ApiOperation(value = "Liste des patients", notes = "Retourne la liste de tous les patients.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Liste des patients récupérée avec succès")
    })
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @ApiOperation(value = "Ajouter un patient", notes = "Ajoute un nouveau patient dans la base de données.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Patient ajouté avec succès")
    })
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(patient));
    }

    @ApiOperation(value = "Récupérer un patient par ID", notes = "Retourne les détails d'un patient spécifique.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Patient récupéré avec succès"),
            @ApiResponse(code = 404, message = "Patient introuvable")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @ApiOperation(value = "Mettre à jour un patient", notes = "Met à jour les détails d'un patient existant.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Patient mis à jour avec succès"),
            @ApiResponse(code = 404, message = "Patient introuvable")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        return ResponseEntity.ok(patientService.updatePatient(id, updatedPatient));
    }

    @ApiOperation(value = "Supprimer un patient", notes = "Supprime un patient spécifique par ID.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Patient supprimé avec succès"),
            @ApiResponse(code = 404, message = "Patient introuvable")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Historique médical d'un patient", notes = "Retourne l'historique médical d'un patient par son nom.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Historique médical récupéré avec succès"),
            @ApiResponse(code = 404, message = "Patient introuvable")
    })
    @GetMapping("/history/{name}")
    public ResponseEntity<List<String>> getMedicalHistoryByName(@PathVariable String name) {
        return ResponseEntity.ok(patientService.getMedicalHistoryByName(name));
    }

    // Gestion des consultations

    @ApiOperation(value = "Liste des consultations", notes = "Retourne la liste de toutes les consultations.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Liste des consultations récupérée avec succès")
    })
    @GetMapping("/consultations")
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        return ResponseEntity.ok(patientService.getAllConsultations());
    }

    @ApiOperation(value = "Ajouter une consultation", notes = "Ajoute une nouvelle consultation.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Consultation ajoutée avec succès")
    })
    @PostMapping("/consultations")
    public ResponseEntity<Consultation> addConsultation(@RequestBody Consultation consultation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addConsultation(consultation));
    }

    @ApiOperation(value = "Récupérer une consultation par ID", notes = "Retourne les détails d'une consultation spécifique.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consultation récupérée avec succès"),
            @ApiResponse(code = 404, message = "Consultation introuvable")
    })
    @GetMapping("/consultations/{id}")
    public ResponseEntity<Consultation> getConsultation(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getConsultationById(id));
    }

    @ApiOperation(value = "Mettre à jour une consultation", notes = "Met à jour les détails d'une consultation existante.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consultation mise à jour avec succès"),
            @ApiResponse(code = 404, message = "Consultation introuvable")
    })
    @PutMapping("/consultations/{id}")
    public ResponseEntity<Consultation> updateConsultation(@PathVariable Long id, @RequestBody Consultation updatedConsultation) {
        return ResponseEntity.ok(patientService.updateConsultation(id, updatedConsultation));
    }

    @ApiOperation(value = "Supprimer une consultation", notes = "Supprime une consultation spécifique par ID.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Consultation supprimée avec succès"),
            @ApiResponse(code = 404, message = "Consultation introuvable")
    })
    @DeleteMapping("/consultations/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        patientService.deleteConsultation(id);
        return ResponseEntity.noContent().build();
    }
}


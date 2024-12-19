package com.example.patientservice.Controllers;

import com.example.patientservice.Model.Patient;
import com.example.patientservice.Services.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Gestion des Patients", description = "Opérations pour gérer les patients et leurs antécédents médicaux")
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @ApiOperation(value = "Récupérer tous les patients", notes = "Retourne la liste de tous les patients.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Liste des patients récupérée avec succès")
    })
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @ApiOperation(value = "Ajouter un patient", notes = "Ajoute un nouveau patient.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Patient ajouté avec succès")
    })
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(patient));
    }

    @ApiOperation(value = "Récupérer un patient par ID", notes = "Retourne les détails d'un patient par son ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Patient récupéré avec succès"),
            @ApiResponse(code = 404, message = "Patient introuvable")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @ApiOperation(value = "Mettre à jour un patient", notes = "Met à jour les informations d'un patient existant.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Patient mis à jour avec succès"),
            @ApiResponse(code = 404, message = "Patient introuvable")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient updatedPatient) {
        return ResponseEntity.ok(patientService.updatePatient(id, updatedPatient));
    }

    @ApiOperation(value = "Supprimer un patient", notes = "Supprime un patient par son ID.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Patient supprimé avec succès"),
            @ApiResponse(code = 404, message = "Patient introuvable")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Ajouter un antécédent médical", notes = "Ajoute un nouvel antécédent médical pour un patient.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Antécédent médical ajouté avec succès"),
            @ApiResponse(code = 404, message = "Patient introuvable")
    })
    @PostMapping("/{id}/medical-history")
    public ResponseEntity<Void> addMedicalHistory(@PathVariable int id, @RequestBody String record) {
        patientService.addMedicalHistory(id, record);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Récupérer l'historique médical", notes = "Retourne l'historique médical d'un patient par son ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Historique médical récupéré avec succès"),
            @ApiResponse(code = 404, message = "Patient introuvable")
    })
    @GetMapping("/{id}/medical-history")
    public ResponseEntity<List<String>> getMedicalHistory(@PathVariable int id) {
        return ResponseEntity.ok(patientService.getMedicalHistory(id));
    }
}

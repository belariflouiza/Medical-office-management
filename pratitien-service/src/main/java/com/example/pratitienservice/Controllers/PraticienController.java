package com.example.pratitienservice.Controllers;



import com.example.pratitienservice.Model.Praticien;
import com.example.pratitienservice.Services.PraticienService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Gestion des praticiens", description = "Opérations liées aux praticiens")
@RestController
@RequestMapping("/api/practitioners")
public class PraticienController {

    private final PraticienService practitionerService;

    public PraticienController(PraticienService practitionerService) {
        this.practitionerService = practitionerService;
    }

    @ApiOperation(value = "Liste des praticiens", notes = "Retourne la liste de tous les praticiens")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Liste des praticiens récupérée avec succès")
    })
    @GetMapping
    public ResponseEntity<List<Praticien>> getAllPractitioners() {
        return ResponseEntity.ok(practitionerService.getAllPractitioners());
    }

    @ApiOperation(value = "Ajouter un praticien", notes = "Ajoute un nouveau praticien")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Praticien ajouté avec succès")
    })
    @PostMapping
    public ResponseEntity<Praticien> addPractitioner(@RequestBody Praticien practitioner) {
        return ResponseEntity.status(HttpStatus.CREATED).body(practitionerService.addPractitioner(practitioner));
    }

    @ApiOperation(value = "Récupérer un praticien par ID", notes = "Retourne les détails d'un praticien spécifique")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Praticien récupéré avec succès"),
            @ApiResponse(code = 404, message = "Praticien introuvable")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Praticien> getPractitionerById(@PathVariable int id) {
        return ResponseEntity.ok(practitionerService.getPractitionerById(id));
    }

    @ApiOperation(value = "Mettre à jour un praticien", notes = "Met à jour les informations d'un praticien")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Praticien mis à jour avec succès"),
            @ApiResponse(code = 404, message = "Praticien introuvable")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Praticien> updatePractitioner(@PathVariable int id, @RequestBody Praticien updatedPractitioner) {
        return ResponseEntity.ok(practitionerService.updatePractitioner(id, updatedPractitioner));
    }

    @ApiOperation(value = "Supprimer un praticien", notes = "Supprime un praticien spécifique")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Praticien supprimé avec succès"),
            @ApiResponse(code = 404, message = "Praticien introuvable")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePractitioner(@PathVariable int id) {
        practitionerService.deletePractitioner(id);
        return ResponseEntity.noContent().build();
    }
}


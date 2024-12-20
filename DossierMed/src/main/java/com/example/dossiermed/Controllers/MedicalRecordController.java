package com.example.dossiermed.Controllers;

import com.example.dossiermed.Model.MedicalRecord;
import com.example.dossiermed.Services.MedicalRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@Api(tags = "Dossiers Médicaux", description = "Endpoints pour gérer les dossiers médicaux.")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping
    @ApiOperation("Récupérer tous les dossiers médicaux.")
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }

    @PostMapping
    @ApiOperation("Créer un nouveau dossier médical.")
    public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord record) {
        return medicalRecordService.createMedicalRecord(record);
    }

    @GetMapping("/{id}")
    @ApiOperation("Récupérer un dossier médical par ID.")
    public MedicalRecord getMedicalRecordById(@PathVariable int id) {
        return medicalRecordService.getMedicalRecordById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("Mettre à jour un dossier médical.")
    public MedicalRecord updateMedicalRecord(@PathVariable int id, @RequestBody MedicalRecord record) {
        return medicalRecordService.updateMedicalRecord(id, record);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Supprimer un dossier médical.")
    public boolean deleteMedicalRecord(@PathVariable int id) {
        return medicalRecordService.deleteMedicalRecord(id);
    }
}

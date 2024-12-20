package com.example.appoitmentservice.Controllers;

import com.example.appoitmentservice.Model.Appointment;
import com.example.appoitmentservice.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        try {
            return appointmentService.addAppointment(appointment);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du rendez-vous", e);
        }
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable int id) {
        return appointmentService.getAppointmentById(id);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAppointment(@PathVariable int id) {
        return appointmentService.deleteAppointment(id);
    }
}

package com.spring.hospital.controller;

import com.spring.hospital.model.Appointment;
import com.spring.hospital.model.DoctorAvailability;
import com.spring.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/availability")
    public DoctorAvailability addAvailability(@RequestBody DoctorAvailability availability) {
        return appointmentService.addAvailability(availability);
    }

    @GetMapping("/availability/{doctorId}")
    public List<DoctorAvailability> getAvailability(@PathVariable Long doctorId) {
        return appointmentService.getAvailabilityForDoctor(doctorId);
    }

    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    @PutMapping("/{appointmentId}/status")
    public Appointment updateStatus(@PathVariable Long appointmentId, @RequestParam String status) {
        return appointmentService.updateStatus(appointmentId, status);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getByDoctor(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsByDoctor(doctorId);
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getByPatient(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    // Endpoint to delete all doctor availability records
    @DeleteMapping("/availability/delete")
    public String deleteAllDoctorAvailabilities() {
        appointmentService.deleteAllAvailabilities();
        return "All doctor availability records deleted successfully";
    }
}

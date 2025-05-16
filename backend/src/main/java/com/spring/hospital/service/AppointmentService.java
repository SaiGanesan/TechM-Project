package com.spring.hospital.service;

import com.spring.hospital.model.Appointment;
import com.spring.hospital.model.DoctorAvailability;
import com.spring.hospital.repository.AppointmentRepository;
import com.spring.hospital.repository.DoctorAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorAvailabilityRepository availabilityRepository;

    public DoctorAvailability addAvailability(DoctorAvailability availability) {
        return availabilityRepository.save(availability);
    }

    public List<DoctorAvailability> getAvailabilityForDoctor(Long doctorId) {
        return availabilityRepository.findByDoctorId(doctorId);
    }

    public Appointment bookAppointment(Appointment appointment) {
        List<DoctorAvailability> slots = availabilityRepository.findByDoctorId(appointment.getDoctorId());

        boolean isValidSlot = slots.stream().anyMatch(slot ->
            !appointment.getAppointmentTime().isBefore(slot.getStartTime()) &&
            !appointment.getAppointmentTime().isAfter(slot.getEndTime())
        );

        if (!isValidSlot) {
            throw new RuntimeException("Appointment time is not within available slot.");
        }

        appointment.setStatus("PENDING");
        return appointmentRepository.save(appointment);
    }

    public Appointment updateStatus(Long appointmentId, String status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (!status.equals("CONFIRMED") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Invalid status. Use CONFIRMED or REJECTED.");
        }

        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // Method to delete all doctor availability records
    public void deleteAllAvailabilities() {
        availabilityRepository.deleteAll();
    }
}

package com.spring.hospital.controller;

import com.spring.hospital.model.Patient;
import com.spring.hospital.model.PatientRecord;
import com.spring.hospital.service.PatientService;
import com.spring.hospital.service.PatientRecordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;
    private final PasswordEncoder passwordEncoder;
    private final PatientRecordService patientRecordService;

    public PatientController(PatientService patientService,
                             PasswordEncoder passwordEncoder,
                             PatientRecordService patientRecordService) {
        this.patientService = patientService;
        this.passwordEncoder = passwordEncoder;
        this.patientRecordService = patientRecordService;
    }

    // Show signup form
    @GetMapping("/signup")
    public String showSignupForm() {
        return "patient/patient_signup";
    }

    // Handle signup form submission
    @PostMapping("/signup")
    public String signup(@ModelAttribute Patient patient, Model model) {
        if (patientService.existsByUsername(patient.getUsername())) {
            model.addAttribute("error", "Username already exists!");
            return "patient/patient_signup";
        }

        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patientService.savePatient(patient);
        return "redirect:/patient/signin";
    }

    // Show signin form
    @GetMapping("/signin")
    public String showSigninForm() {
        return "patient/patient_signin";
    }

    // Handle signin form submission
    @PostMapping("/signin")
    public String signin(@ModelAttribute Patient patient, Model model) {
        Patient existingPatient = patientService.findByUsername(patient.getUsername());

        if (existingPatient != null && passwordEncoder.matches(patient.getPassword(), existingPatient.getPassword())) {
            Long patientId = existingPatient.getId();

            List<PatientRecord> records = patientRecordService.getRecordsByPatientId(patientId);
            if (records.isEmpty()) {
                return "redirect:/patient/records/form?patientId=" + patientId;
            } else {
                return "redirect:/patient/records/view?patientId=" + patientId;
            }

        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "patient/patient_signin";
        }
    }
}

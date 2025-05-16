package com.spring.hospital.controller;

import com.spring.hospital.model.Doctor;
import com.spring.hospital.model.DoctorRecord;
import com.spring.hospital.service.DoctorService;
import com.spring.hospital.service.DoctorRecordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final PasswordEncoder passwordEncoder;
    private final DoctorRecordService doctorRecordService;

    public DoctorController(DoctorService doctorService,
                            PasswordEncoder passwordEncoder,
                            DoctorRecordService doctorRecordService) {
        this.doctorService = doctorService;
        this.passwordEncoder = passwordEncoder;
        this.doctorRecordService = doctorRecordService;
    }

    // Show signup form
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor/doctor_signup";
    }

    // Handle signup form submission
    @PostMapping("/signup")
    public String signup(@ModelAttribute Doctor doctor, Model model) {
        if (doctorService.existsByUsername(doctor.getUsername())) {
            model.addAttribute("error", "Username already exists!");
            return "doctor/doctor_signup";
        }

        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctorService.saveDoctor(doctor);
        return "redirect:/doctor/signin";
    }

    // Show signin form
    @GetMapping("/signin")
    public String showSigninForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor/doctor_signin";
    }

    // Handle signin form submission
    @PostMapping("/signin")
    public String signin(@ModelAttribute Doctor doctor, Model model) {
        Doctor existingDoctor = doctorService.findByUsername(doctor.getUsername());

        if (existingDoctor != null && passwordEncoder.matches(doctor.getPassword(), existingDoctor.getPassword())) {
            Long doctorId = existingDoctor.getId();

            List<DoctorRecord> records = doctorRecordService.getRecordsByDoctorId(doctorId);
            if (records.isEmpty()) {
                return "redirect:/doctor/records/form?doctorId=" + doctorId;
            } else {
                return "redirect:/doctor/records/view?doctorId=" + doctorId;
            }

        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "doctor/doctor_signin";
        }
    }
}

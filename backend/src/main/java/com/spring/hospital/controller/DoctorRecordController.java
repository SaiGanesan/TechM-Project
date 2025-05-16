package com.spring.hospital.controller;

import com.spring.hospital.model.DoctorRecord;
import com.spring.hospital.service.DoctorRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctor/records")
public class DoctorRecordController {

    private final DoctorRecordService doctorRecordService;

    public DoctorRecordController(DoctorRecordService doctorRecordService) {
        this.doctorRecordService = doctorRecordService;
    }

    // Show form for new or existing doctor record
    @GetMapping("/form")
    public String showForm(@RequestParam Long doctorId, Model model) {
        List<DoctorRecord> records = doctorRecordService.getRecordsByDoctorId(doctorId);

        DoctorRecord doctorRecord;
        if (records.isEmpty()) {
            doctorRecord = new DoctorRecord();
            doctorRecord.setDoctorId(doctorId);
        } else {
            doctorRecord = records.get(0);
        }

        model.addAttribute("doctorRecord", doctorRecord);
        return "doctor/doctor_record_form";
    }

    // Save or update the doctor record
    @PostMapping("/save")
    public String saveRecord(@ModelAttribute DoctorRecord doctorRecord) {
        doctorRecordService.createDoctor(doctorRecord); // handles both save & update
        return "redirect:/doctor/records/view?doctorId=" + doctorRecord.getDoctorId();
    }

    // View doctor profile
    @GetMapping("/view")
    public String viewRecord(@RequestParam Long doctorId, Model model) {
        List<DoctorRecord> records = doctorRecordService.getRecordsByDoctorId(doctorId);

        DoctorRecord doctorRecord;
        if (records.isEmpty()) {
            return "redirect:/doctor/records/form?doctorId=" + doctorId;
        } else {
            doctorRecord = records.get(0);
        }

        model.addAttribute("doctorRecord", doctorRecord);
        return "doctor/doctor_record_view";
    }

    // Edit existing record
    @GetMapping("/edit")
    public String editRecord(@RequestParam Long doctorId, Model model) {
        List<DoctorRecord> records = doctorRecordService.getRecordsByDoctorId(doctorId);

        if (records.isEmpty()) {
            return "redirect:/doctor/records/form?doctorId=" + doctorId;
        }

        model.addAttribute("doctorRecord", records.get(0));
        return "doctor/doctor_record_form";
    }

    // Delete doctor record and redirect
    @GetMapping("/delete/{id}")
    public String deleteDoctorRecord(@PathVariable Long id, @RequestParam Long doctorId) {
        doctorRecordService.deleteDoctor(id);
        return "redirect:/doctor/records/view?doctorId=" + doctorId;
    }
}

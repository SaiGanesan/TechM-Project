package com.spring.hospital.controller;

import com.spring.hospital.model.PatientRecord;
import com.spring.hospital.service.PatientRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient/records")
public class PatientRecordController {

    private final PatientRecordService service;

    public PatientRecordController(PatientRecordService service) {
        this.service = service;
    }

    // Show form for new or editing record
    @GetMapping("/form")
    public String showForm(@RequestParam Long patientId, Model model) {
        List<PatientRecord> records = service.getRecordsByPatientId(patientId);

        PatientRecord record;
        if (records.isEmpty()) {
            record = new PatientRecord();
            record.setPatientId(patientId);
        } else {
            record = records.get(0);
        }
        model.addAttribute("record", record);
        return "patient/patient_record_form";
    }

    // Save or update record
    @PostMapping("/save")
    public String saveRecord(@ModelAttribute PatientRecord record) {
        service.addRecord(record);
        return "redirect:/patient/records/view?patientId=" + record.getPatientId();
    }

    // View saved personal details
    @GetMapping("/view")
    public String viewRecord(@RequestParam Long patientId, Model model) {
        List<PatientRecord> records = service.getRecordsByPatientId(patientId);
        if (records.isEmpty()) {
            return "redirect:/patient/records/form?patientId=" + patientId;
        }
        model.addAttribute("record", records.get(0));
        return "patient/patient_record_view";
    }

    // Edit existing record
    @GetMapping("/edit")
    public String editRecord(@RequestParam Long patientId, Model model) {
        List<PatientRecord> records = service.getRecordsByPatientId(patientId);
        if (records.isEmpty()) {
            return "redirect:/patient/records/form?patientId=" + patientId;
        }
        model.addAttribute("record", records.get(0));
        return "patient/patient_record_form";
    }
}

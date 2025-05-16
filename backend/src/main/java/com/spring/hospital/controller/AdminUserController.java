package com.spring.hospital.controller;

import com.spring.hospital.model.AdminUser;
import com.spring.hospital.model.DoctorRecord;
import com.spring.hospital.model.PatientRecord;
import com.spring.hospital.service.AdminUserService;
import com.spring.hospital.service.DoctorRecordService;
import com.spring.hospital.service.PatientRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private PatientRecordService patientRecordService;

    @Autowired
    private DoctorRecordService doctorRecordService;

    // ==================== Admin's own record ====================

    @GetMapping("/records/view")
    public String viewAdminRecord(@RequestParam Long adminId, Model model) {
        AdminUser admin = adminUserService.getAdminById(adminId);
        model.addAttribute("admin", admin);
        return "admin/admin_record_view";
    }

    @GetMapping("/records/edit/{id}")
    public String showEditAdminForm(@PathVariable Long id, Model model) {
        AdminUser admin = adminUserService.getAdminById(id);
        model.addAttribute("admin", admin);
        return "admin/admin_record_form";
    }

    @PostMapping("/records/edit/{id}")
    public String updateAdminRecord(@PathVariable Long id, @ModelAttribute AdminUser admin) {
        adminUserService.updateAdmin(id, admin);
        return "redirect:/admin/records/view?adminId=" + id;
    }

    // ==================== Doctor records ====================

    @GetMapping("/view-doctors")
    public String viewDoctorRecords(Model model) {
        List<DoctorRecord> doctors = doctorRecordService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "admin/view_doctor_records";
    }

    @GetMapping("/doctor/view/{id}")
    public String viewDoctorRecord(@PathVariable Long id, Model model) {
        DoctorRecord doctor = doctorRecordService.getDoctorById(id);
        model.addAttribute("doctorRecord", doctor);
        return "admin/doctor_record_view";
    }

    @GetMapping("/doctor/edit/{id}")
    public String showEditDoctorForm(@PathVariable Long id, Model model) {
        DoctorRecord doctor = doctorRecordService.getDoctorById(id);
        model.addAttribute("doctorRecord", doctor);
        return "admin/doctor_record_form";
    }

    @PostMapping("/doctor/edit/{id}")
    public String updateDoctorRecord(@PathVariable Long id, @ModelAttribute DoctorRecord doctorRecord) {
        doctorRecordService.updateDoctor(id, doctorRecord);
        return "redirect:/admin/doctor/view/" + id;
    }

    // ==================== Patient records ====================

    @GetMapping("/view-patients")
    public String viewPatientRecords(Model model) {
        List<PatientRecord> patients = patientRecordService.getAllRecords();
        model.addAttribute("patients", patients);
        return "admin/view_patient_records";
    }

    @GetMapping("/patient/view/{id}")
    public String viewPatientRecord(@PathVariable Long id, Model model) {
        PatientRecord patient = patientRecordService.getRecordById(id);
        model.addAttribute("patientRecord", patient);
        return "admin/patient_record_view";
    }

    @GetMapping("/patient/edit/{id}")
    public String showEditPatientForm(@PathVariable Long id, Model model) {
        PatientRecord patient = patientRecordService.getRecordById(id);
        model.addAttribute("patientRecord", patient);
        return "admin/patient_record_form";
    }

    @PostMapping("/patient/edit/{id}")
    public String updatePatientRecord(@PathVariable Long id, @ModelAttribute PatientRecord patientRecord) {
        patientRecordService.updateRecord(id, patientRecord);
        return "redirect:/admin/patient/view/" + id;
    }
}

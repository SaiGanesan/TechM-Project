package com.spring.hospital.controller;

import com.spring.hospital.model.Admin;
import com.spring.hospital.service.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(AdminService adminService, PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    // Show signup form
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/admin_signup";
    }

    // Handle signup form submission
    @PostMapping("/signup")
    public String signup(@ModelAttribute Admin admin, Model model) {
        if (adminService.existsByUsername(admin.getUsername())) {
            model.addAttribute("error", "Username already exists!");
            return "admin/admin_signup";
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminService.saveAdmin(admin);
        return "redirect:/admin/signin";
    }

    // Show signin form
    @GetMapping("/signin")
    public String showSigninForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/admin_signin";
    }

    // Handle signin form submission
    @PostMapping("/signin")
    public String signin(@ModelAttribute Admin admin, Model model) {
        Admin existingAdmin = adminService.findByUsername(admin.getUsername());

        if (existingAdmin != null && passwordEncoder.matches(admin.getPassword(), existingAdmin.getPassword())) {
            Long adminId = existingAdmin.getId();
            return "redirect:/admin/dashboard?adminId=" + adminId;
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "admin/admin_signin";
        }
    }

    // Admin dashboard with 3 buttons
    @GetMapping("/dashboard")
    public String showDashboard(@RequestParam Long adminId, Model model) {
        model.addAttribute("adminId", adminId);
        return "admin/admin_dashboard";
    }
}

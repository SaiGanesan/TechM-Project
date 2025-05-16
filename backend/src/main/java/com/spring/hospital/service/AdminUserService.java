package com.spring.hospital.service;

import com.spring.hospital.model.AdminUser;
import com.spring.hospital.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    // Add new admin
    public AdminUser addAdmin(AdminUser adminUser) {
        return adminUserRepository.save(adminUser);
    }

    // Get all admins
    public List<AdminUser> getAllAdmins() {
        return adminUserRepository.findAll();
    }

    // Get admin by ID
    public AdminUser getAdminById(Long id) {
        Optional<AdminUser> adminUser = adminUserRepository.findById(id);
        return adminUser.orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    // Update admin details
    public AdminUser updateAdmin(Long id, AdminUser adminUser) {
        AdminUser existingAdmin = getAdminById(id);
        existingAdmin.setFullName(adminUser.getFullName());
        existingAdmin.setEmailAddress(adminUser.getEmailAddress()); // Use getEmailAddress()
        existingAdmin.setContactNumber(adminUser.getContactNumber());
        return adminUserRepository.save(existingAdmin);
    }

    // Delete admin
    public void deleteAdmin(Long id) {
        AdminUser adminUser = getAdminById(id);
        adminUserRepository.delete(adminUser);
    }
}

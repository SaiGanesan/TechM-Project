package com.spring.hospital.repository;

import com.spring.hospital.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByUsername(String username);
    Admin findByUsername(String username);
}

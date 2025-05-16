package com.spring.hospital.repository;

import com.spring.hospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByUsername(String username);
    boolean existsByUsername(String username);
   
}

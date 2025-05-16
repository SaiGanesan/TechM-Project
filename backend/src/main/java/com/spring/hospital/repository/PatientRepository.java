package com.spring.hospital.repository;

import com.spring.hospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByUsername(String username);
    Patient findByUsername(String username);
}

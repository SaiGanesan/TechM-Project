package com.spring.hospital.service;

import com.spring.hospital.model.Patient;
import com.spring.hospital.repository.PatientRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@Service
public class PatientService implements UserDetailsService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepository.findByUsername(username);
        if (patient == null) {
            throw new UsernameNotFoundException("Patient not found with username: " + username);
        }

        return new User(
            patient.getUsername(),
            patient.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_PATIENT"))
        );
    }

    public boolean existsByUsername(String username) {
        return patientRepository.existsByUsername(username);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findByUsername(String username) {
        return patientRepository.findByUsername(username);
    }
}

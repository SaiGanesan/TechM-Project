package com.spring.hospital.service;

import com.spring.hospital.model.Doctor;
import com.spring.hospital.repository.DoctorRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@Service
public class DoctorService implements UserDetailsService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByUsername(username);
        if (doctor == null) {
            throw new UsernameNotFoundException("Doctor not found with username: " + username);
        }

        return new User(
            doctor.getUsername(),
            doctor.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_DOCTOR"))
        );
    }

    public boolean existsByUsername(String username) {
        return doctorRepository.existsByUsername(username);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor findByUsername(String username) {
        return doctorRepository.findByUsername(username);
    }
}

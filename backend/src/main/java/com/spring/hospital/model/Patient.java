package com.spring.hospital.model;

import jakarta.persistence.Entity;

@Entity
public class Patient extends User {
    public Patient() {
        setRole(Role.PATIENT);
    }
    
}

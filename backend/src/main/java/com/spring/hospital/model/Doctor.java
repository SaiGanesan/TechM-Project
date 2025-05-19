package com.spring.hospital.model;

import jakarta.persistence.Entity;

@Entity
public class Doctor extends User {
    public Doctor() {
        setRole(Role.DOCTOR);
    }
    
}

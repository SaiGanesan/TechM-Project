package com.spring.hospital.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {
    public Admin() {
        setRole(Role.ADMIN);
    }
    
}

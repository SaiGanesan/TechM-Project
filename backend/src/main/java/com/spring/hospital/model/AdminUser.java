package com.spring.hospital.model;

import jakarta.persistence.*;

@Entity
public class AdminUser extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This class only inherits fullName, contactNumber, emailAddress from Person

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

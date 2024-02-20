package com.fbla.parters.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String organizationName; // name of partner
    private String type; // e.g., NGO, Corporation, etc.

    private String contactEmail; // contact
    private String contactPhone;
    private String contactUrl;
    private String description;

    @ManyToMany
    private Set<Tag> tags;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

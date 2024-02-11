package com.fbla.parters.model;



import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // name of partner
    private String type; // e.g., NGO, Corporation, etc.
    private String resources; // Description of resources available
    private String contactName; // name of contact
    private String contactEmail; // contact 
    private String contactPhone;
    private String aboutMe;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    

    // Standard getters and setters
}

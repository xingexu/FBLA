package com.fbla.parters.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name; // name of partner
    private String type; // e.g., NGO, Corporation, etc.
    private String resources; // Description of resources available
    private String contactName; // name of contact
    private String contactEmail; // contact 
    private String contactPhone;
    private String aboutMe;
    

    // Standard getters and setters
}

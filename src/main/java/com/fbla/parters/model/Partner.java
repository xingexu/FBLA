package com.fbla.parters.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Organization Name cannot be empty.")
    @Size(min = 2, max = 255)
    private String organizationName; // Name of the partner organization

    @NotEmpty(message = "Type cannot be empty.")
    @Size(min = 1, max = 255)
    private String type; // Type of organization, e.g., NGO, Corporation, etc.

    @NotEmpty(message = "Contact Email cannot be empty.")
    @Size(min = 1, max = 255)
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$$", message = "Please provide a valid email address")
    private String contactEmail; // Contact email address
    
    @NotEmpty(message = "Contact Phone cannot be empty.")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[-.\\s]?(\\d{3})[-.\\s]?(\\d{4})$", message = "Please provide a valid phone number")
    private String contactPhone; // Contact phone number
    
    private String contactUrl; // Contact URL
    private String logoPath; // Path to the logo image
    private String description; // Description of the partner

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "partner_tag",
        joinColumns = @JoinColumn(name = "partner_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>(); // Set of associated tags

    private LocalDateTime createdAt; // Timestamp of creation
    private LocalDateTime updatedAt; // Timestamp of last update
}

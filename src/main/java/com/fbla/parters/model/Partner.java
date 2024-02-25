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
    private String organizationName; // name of partner

    @NotEmpty(message = "type cannot be empty.")
    @Size(min = 1, max = 255)
    private String type; // e.g., NGO, Corporation, etc.

    @NotEmpty(message = "contactEmail cannot be empty.")
    @Size(min = 1, max = 255)
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$$", message = "Please provide a valid email address")
    private String contactEmail; // contact
    
    @NotEmpty(message = "contactPhone cannot be empty.")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[-.\\s]?(\\d{3})[-.\\s]?(\\d{4})$", message = "Please provide a valid phone number")
    private String contactPhone;
    
    private String contactUrl;
    private String logoPath;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "partner_tag",
        joinColumns = @JoinColumn(name = "partner_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

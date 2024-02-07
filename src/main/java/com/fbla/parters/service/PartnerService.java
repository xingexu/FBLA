package com.fbla.parters.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fbla.parters.model.Partner;
import com.fbla.parters.repository.PartnerRepository;

import lombok.NonNull;

@Service
public class PartnerService {

    private final PartnerRepository partnerRepository;

    // Constructor for PartnerService, which takes a PartnerRepository as a parameter
    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    // Method to find and retrieve all partners from the repository
    public List<Partner> findAll() {
        return partnerRepository.findAll();
    }

    // Method to save a new partner or update an existing one
    public void save(@NonNull Partner partner) {
        partnerRepository.save(partner);
    }

    // Method to delete a partner by their ID
    public void delete(Long id) {
        partnerRepository.deleteById(id);
    }

    // Additional methods to handle search and filter
    // (These methods are not present in the code but can be added here)
}

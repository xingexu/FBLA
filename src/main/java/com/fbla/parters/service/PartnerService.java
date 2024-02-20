package com.fbla.parters.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fbla.parters.model.Partner;
import com.fbla.parters.model.Tag;
import com.fbla.parters.repository.PartnerRepository;
import com.fbla.parters.repository.TagRepository;

import lombok.NonNull;

@Service
public class PartnerService {

    private final PartnerRepository partnerRepository;

    private final TagRepository tagRepository;

    // Constructor for PartnerService, which takes a PartnerRepository and a
    // TagRepository as a
    // parameter
    public PartnerService(PartnerRepository partnerRepository, TagRepository tagRepository) {
        this.partnerRepository = partnerRepository;
        this.tagRepository = tagRepository;
    }

    // Method to find and retrieve all partners from the repository
    public List<Partner> findAll() {
        return partnerRepository.findAll();
    }

    // Method to save a new partner or update an existing one
    public void save(@NonNull Partner partner) {
        partner.setCreatedAt(LocalDateTime.now());
        partner.setUpdatedAt(LocalDateTime.now());
        partnerRepository.save(partner);
    }

    // Method to delete a partner by their ID
    public void delete(Long id) {
        partnerRepository.deleteById(id);
    }

    // Method to find a partners by a keyword
    public List<Partner> findByKeyword(String keyword) {
        return partnerRepository.findByKeyword(keyword);
    }

    public List<Partner> filterItemsByTags(Set<Tag> tags) {
        return partnerRepository.findByTagsIn(tags);
    }

    public List<Partner> filterItemsByTagsAndKeyword(Set<Tag> tags, String keyword) {
        return partnerRepository.findByTagsIn(tags).stream()
                .filter(partner -> partner.getOrganizationName().contains(keyword)
                        || partner.getType().contains(keyword) || partner.getContactEmail().contains(keyword)
                        || partner.getDescription().contains(keyword))
                .toList();
    }

    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    public Set<Tag> findByTagIds(Set<Long> tagIds) {
        return tagRepository.findAllById(Objects.requireNonNull(tagIds)).stream().collect(Collectors.toSet());
    }

    // Additional methods to handle search and filter
    // (These methods are not present in the code but can be added here)
}

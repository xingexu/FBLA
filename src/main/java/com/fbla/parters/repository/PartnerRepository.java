package com.fbla.parters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fbla.parters.model.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    // Custom query methods can be added here
}

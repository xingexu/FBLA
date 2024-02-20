package com.fbla.parters.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fbla.parters.model.Partner;
import com.fbla.parters.model.Tag;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Query(value = "SELECT * FROM Partner p WHERE LOWER(p.organizationName) LIKE LOWER(%:keyword%) OR LOWER(p.type) LIKE LOWER( %:keyword%)  OR LOWER( p.description)  LIKE LOWER( %:keyword%)  OR LOWER( p.contactEmail) LIKE LOWER( %:keyword%) OR p.contactPhone LIKE %:keyword%", nativeQuery = true)
    List<Partner> findByKeyword(@Param("keyword") String keyword);

    List<Partner> findByTagsIn(Set<Tag> tags);

}

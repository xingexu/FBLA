package com.fbla.parters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbla.parters.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
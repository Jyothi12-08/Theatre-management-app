package com.TheatreManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TheatreManagement.entity.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    // Additional custom query methods can be defined here if needed
}
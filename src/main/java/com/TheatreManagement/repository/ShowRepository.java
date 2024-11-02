package com.TheatreManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TheatreManagement.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByTheatreId(Long theatreId);
}
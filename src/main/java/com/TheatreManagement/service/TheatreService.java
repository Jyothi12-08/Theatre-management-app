package com.TheatreManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TheatreManagement.entity.Theatre;
import com.TheatreManagement.repository.TheatreRepository;

import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public Theatre getTheatreById(Long id) {
        return theatreRepository.findById(id).orElseThrow(() -> new RuntimeException("Theatre not found"));
    }

    public Theatre addTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public Theatre updateTheatre(Long id, Theatre updatedTheatre) {
        Theatre theatre = getTheatreById(id);
        theatre.setName(updatedTheatre.getName());
        theatre.setLocation(updatedTheatre.getLocation());
        return theatreRepository.save(theatre);
    }

    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }
}

package com.TheatreManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TheatreManagement.entity.Theatre;
import com.TheatreManagement.service.TheatreService;

@RestController
@RequestMapping("/theatres")
public class TheatreController {
	
	@Autowired
    private TheatreService theatreService;

    @GetMapping
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    @GetMapping("/{id}")
    public Theatre getTheatreById(@PathVariable Long id) {
        return theatreService.getTheatreById(id);
    }

    @PostMapping
    public Theatre addTheatre(@RequestBody Theatre theatre) {
        return theatreService.addTheatre(theatre);
    }

    @PutMapping("/{id}")
    public Theatre updateTheatre(@PathVariable Long id, @RequestBody Theatre theatre) {
        return theatreService.updateTheatre(id, theatre);
    }

    @DeleteMapping("/{id}")
    public void deleteTheatre(@PathVariable Long id) {
        theatreService.deleteTheatre(id);
    }


}

package com.TheatreManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.TheatreManagement.entity.Show;
import com.TheatreManagement.service.ShowService;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

	@Autowired
	private ShowService showService;

	@GetMapping("/theatre/{theatreId}")
	public List<Show> getAllShowsForTheatre(@PathVariable Long theatreId) {
		return showService.getAllShowsForTheatre(theatreId);
	}

	@GetMapping("/{id}")
	public Show getShowById(@PathVariable Long id) {
		return showService.getShowById(id);
	}

	@PostMapping
	public ResponseEntity<Show> addShow(@RequestBody Show show) {
		Show showRes = showService.addShow(show);
		return new ResponseEntity<>(showRes, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public Show updateShow(@PathVariable Long id, @RequestBody Show show) {
		return showService.updateShow(id, show);
	}

	@DeleteMapping("/{id}")
	public void deleteShow(@PathVariable Long id) {
		showService.deleteShow(id);
	}
	
	@PostMapping("/{showId}/addSeats")
    public ResponseEntity<String> addSeats(@PathVariable Long showId, @RequestParam int numberOfSeats) {
        showService.addSeats(showId, numberOfSeats);
        return new ResponseEntity<>("Seats added successfully", HttpStatus.OK);
    }
}

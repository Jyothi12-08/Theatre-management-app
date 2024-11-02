package com.TheatreManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.TheatreManagement.dto.BookedSeatResponse;
import com.TheatreManagement.entity.Seat;
import com.TheatreManagement.service.SeatService;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/show/{showId}")
    public List<Seat> getSeatsByShow(@PathVariable Long showId) {
        return seatService.getSeatsByShowId(showId);
    }

    @PostMapping("/book")
    public BookedSeatResponse bookSeat(@RequestParam Long showId, @RequestParam int seatNumber) {
        return seatService.bookSeat(showId, seatNumber);
    }
    
    @PostMapping("/bookInBulk")
    public BookedSeatResponse bookInBulk(@RequestParam Long showId, @RequestParam int startingSeatNumber, @RequestParam int endingSeatNumber) {
    	return seatService.bookSeatInBulk(showId, startingSeatNumber, endingSeatNumber);
    }
    
}

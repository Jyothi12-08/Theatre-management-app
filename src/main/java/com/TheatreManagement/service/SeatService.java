package com.TheatreManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TheatreManagement.dto.BookedSeatResponse;
import com.TheatreManagement.entity.Seat;
import com.TheatreManagement.entity.Show;
import com.TheatreManagement.repository.SeatRepository;
import com.TheatreManagement.repository.ShowRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;
    
    @Autowired
    private ShowRepository showRepository;
    
    @Autowired
    private DiscountService discountService;

    public List<Seat> getSeatsByShowId(Long showId) {
        return seatRepository.findByShowId(showId);
    }

    @Transactional
    public BookedSeatResponse bookSeat(Long showId, int seatNumber) {
        Seat seat = seatRepository.findByShowId(showId)
                .stream()
                .filter(s -> s.getSeatNumber() == seatNumber && s.isAvailable())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Seat not available"));

        seat.setAvailable(false);
        seatRepository.save(seat);

        Show show = seat.getShow();
        show.setAvailableSeats(show.getAvailableSeats() - 1);
        showRepository.save(show);
        return discountService.applyDiscount(show, 1) ;
    }
    
    @Transactional
    public BookedSeatResponse bookSeatInBulk(Long showId, int startingSeatNumber,int endingSeatNumber) {
    
    	if (startingSeatNumber > endingSeatNumber) {
            throw new IllegalArgumentException("Starting seat number must be less than or equal to the ending seat number.");
        }
    	
        List<Seat> seatsToBook = seatRepository.findByShowId(showId).stream()
                .filter(seat -> seat.getSeatNumber() >= startingSeatNumber 
                             && seat.getSeatNumber() <= endingSeatNumber)
                .collect(Collectors.toList());
        
        boolean allSeatsAvailable = seatsToBook.stream().allMatch(Seat::isAvailable);
        if (!allSeatsAvailable) {
            throw new IllegalStateException("Some seats in the specified range are already booked.");
        }
        
        int numberOfTickets = endingSeatNumber-startingSeatNumber;

        seatsToBook.forEach(seat -> seat.setAvailable(false));
        seatRepository.saveAll(seatsToBook);
        
        Show show = seatsToBook.get(0).getShow();
        show.setAvailableSeats(show.getAvailableSeats() - numberOfTickets);
        showRepository.save(show);

        return discountService.applyDiscount(show, numberOfTickets) ;
        
        
    }
}

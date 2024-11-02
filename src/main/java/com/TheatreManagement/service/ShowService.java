package com.TheatreManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TheatreManagement.entity.Seat;
import com.TheatreManagement.entity.Show;
import com.TheatreManagement.entity.Theatre;
import com.TheatreManagement.repository.SeatRepository;
import com.TheatreManagement.repository.ShowRepository;
import com.TheatreManagement.repository.TheatreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;
    
    @Autowired
    private TheatreRepository theatreRepository;
    
    @Autowired
    private SeatRepository seatRepository;

    public List<Show> getAllShowsForTheatre(Long theatreId) {
        return showRepository.findByTheatreId(theatreId);
    }

    public Show getShowById(Long id) {
        return showRepository.findById(id).orElseThrow(() -> new RuntimeException("Show not found"));
    }

    public Show addShow(Show show) {
    	Theatre theatre = theatreRepository.findById(show.getTheatreId())
                .orElseThrow(() -> new RuntimeException("Theatre not found"));
    	
    	if(show.getAvailableSeats()>show.getTotalSeats())
    		throw new RuntimeException("Available Seats Shouldn't be more than Total Seats of " + show.getTotalSeats());
    	
    	Show savedShow = showRepository.save(show);
    	
    	
    	List<Seat> seats = new ArrayList<>();
        for(int i=0;i<=savedShow.getAvailableSeats();i++) {
        	 Seat s1 = new Seat();
             s1.setShow(show);
             s1.setAvailable(true);
             s1.setSeatNumber(i);
             seats.add(s1);
        }
        seatRepository.saveAll(seats);
    	
        return savedShow;
    }

    public Show updateShow(Long id, Show updatedShow) {
        Show show = getShowById(id);
        show.setMovieName(updatedShow.getMovieName());
        show.setStartTime(updatedShow.getStartTime());
        show.setTotalSeats(updatedShow.getTotalSeats());
        show.setAvailableSeats(updatedShow.getAvailableSeats());
        return showRepository.save(show);
    }

    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }
    
    public void addSeats(Long showId, int numberOfSeats) {
        Show show = showRepository.findById(showId)
            .orElseThrow(() -> new RuntimeException("Show not found with id: " + showId));

        int currentSeats = show.getAvailableSeats();
        // Update the available seats
        show.setAvailableSeats(show.getAvailableSeats() + numberOfSeats);

        if(show.getAvailableSeats() > show.getTotalSeats()) {
        	throw new RuntimeException("Available Seats Shouldn't be more than Total Seats of " + show.getTotalSeats());
        }
        
        // Save the updated show
        showRepository.save(show);
        int totalSeats = show.getAvailableSeats();
        List<Seat> seats = new ArrayList<>();
        for(int i=currentSeats+1;i<=totalSeats;i++) {
        	 Seat s1 = new Seat();
             s1.setShow(show);
             s1.setAvailable(true);
             s1.setSeatNumber(i);
             seats.add(s1);
        }
        seatRepository.saveAll(seats);
    }
    
}

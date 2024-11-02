package com.TheatreManagement.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TheatreManagement.dto.BookedSeatResponse;
import com.TheatreManagement.entity.Show;
import com.TheatreManagement.entity.Theatre;

@Service
public class DiscountService {
	
	@Autowired
	private TheatreService theatreService;

	public BookedSeatResponse applyDiscount(Show show,int numberOfTickets) {
		BookedSeatResponse response  = new BookedSeatResponse();
		Theatre theatee = theatreService.getTheatreById(show.getTheatreId());
		double totalPrice = calculateDiscount(show.getStartTime(),numberOfTickets,show.getRate(),response);
		response.setActualRateForEach(show.getRate());
		response.setFinalPrice(totalPrice);
		response.setShowId(show.getId());
		response.setTheatreName(theatee.getName());
		response.setSlot(show.getStartTime().toString());
		response.setTheatreId(show.getTheatreId());
		response.setTotalTickets(numberOfTickets);
		return response;
	}
	
	public static double calculateDiscount(LocalDateTime startTime,int numberOfTickets,double baseRate,BookedSeatResponse response) {
        LocalTime time = startTime.toLocalTime();
        if (time.isAfter(LocalTime.of(12, 0)) && time.isBefore(LocalTime.of(17, 0))) {
        	response.setDiscountType("Mid Day Discount");
        	return baseRate * numberOfTickets * (1 - 0.20);  // 20% discount for afternoon shows
        }else if(numberOfTickets>=3) {
        	int eligibleForDiscount = numberOfTickets / 3;
            int remainingTickets = numberOfTickets - eligibleForDiscount;
            response.setDiscountType("Every 3rd ticket Discount for "+eligibleForDiscount+" tickets");
            // Total price calculation
            double discountedTotal = eligibleForDiscount * baseRate * 0.50; // 20% discount for every third ticket
            double fullPriceTotal = remainingTickets * baseRate;

            double totalPrice = discountedTotal + fullPriceTotal;
            return totalPrice;
        }
        response.setDiscountType("No Discount");
        
        return baseRate*numberOfTickets;  // No discount
    }

}

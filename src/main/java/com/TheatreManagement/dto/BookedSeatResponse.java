package com.TheatreManagement.dto;

public class BookedSeatResponse {
	
	private Long showId;
	private Long theatreId;
	private String theatreName;
	private int totalTickets;
	private String slot;
	private double actualRateForEach;
	private String discountType;
	private double finalPrice;
	
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public Long getShowId() {
		return showId;
	}
	public void setShowId(Long showId) {
		this.showId = showId;
	}
	public Long getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}
	public int getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public double getActualRateForEach() {
		return actualRateForEach;
	}
	public void setActualRateForEach(double actualRateForEach) {
		this.actualRateForEach = actualRateForEach;
	}
	
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
}

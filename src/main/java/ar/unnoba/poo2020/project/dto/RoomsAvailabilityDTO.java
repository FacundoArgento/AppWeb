package ar.unnoba.poo2020.project.dto;

import java.util.Date;
import java.text.SimpleDateFormat;

import java.text.ParseException;

public class RoomsAvailabilityDTO{


	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private String checkIn;
	private String checkOut;
	private int occupancy;
	
	
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	
	public String getCheckOut() {
		return checkOut;
	}
	
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	
	public int getOccupancy() {
		return occupancy;
	}
	public void setOccupancy(int occupancy) {
		this.occupancy = occupancy;
	}
	
	public Date getCheckInDateConverted() throws ParseException {
		return dateFormat.parse(this.checkIn);
	}
	
	public void setCheckInDate(Date date) { this.checkIn = dateFormat.format(date);}
	
	public Date getCheckOutDateConverted() throws ParseException {
		return dateFormat.parse(this.checkOut);
	}
	
	public void setCheckOutDate(Date date) { this.checkOut = dateFormat.format(date);}
	
}

package ar.unnoba.poo2020.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="guest")
	private User guest;
	
	@Column(name = "check_in")
	private Date checkIn;
	
	@Column(name = "check_out")
	private Date checkOut;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private Room room;
	
	@Column(name = "breakfast_included")
	private boolean breakfastIncluded;
	
	@Column(name = "parking")
	private boolean parking;
	
	@Column(name = "free_cancelation")
	private boolean freeCancelation;
	
	@Column(name = "cost")
	private Float cost;
	
	@OneToOne(mappedBy = "booking")
	private Payment payment;
	
	
	
	public Booking(User guest, Date checkIn, Date checkOut, Date createdAt, Room room, boolean breakfastIncluded,
			boolean parking, boolean freeCancelation, Float cost, Payment payment) {
		super();
		this.guest = guest;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.createdAt = createdAt;
		this.room = room;
		this.breakfastIncluded = breakfastIncluded;
		this.parking = parking;
		this.freeCancelation = freeCancelation;
		this.cost = cost;
		this.payment = payment;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getGuest() {
		return guest;
	}
	public void setGuest(User guest) {
		this.guest = guest;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public boolean isBreakfastIncluded() {
		return breakfastIncluded;
	}
	public void setBreakfastIncluded(boolean breakfastIncluded) {
		this.breakfastIncluded = breakfastIncluded;
	}
	public boolean isParking() {
		return parking;
	}
	public void setParking(boolean parking) {
		this.parking = parking;
	}
	public boolean isFreeCancelation() {
		return freeCancelation;
	}
	public void setFreeCancelation(boolean freeCancelation) {
		this.freeCancelation = freeCancelation;
	}
	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
}

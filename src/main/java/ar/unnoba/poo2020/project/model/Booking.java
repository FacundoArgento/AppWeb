package ar.unnoba.poo2020.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	//@Temporal(TemporalType.DATE)
	private Date checkIn;
	
	@Column(name = "check_out")
	//@Temporal(TemporalType.DATE)
	private Date checkOut;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private Room room;
	
	//@Column(name = "breakfast_included")
	//private boolean breakfastIncluded;
	
	//@Column(name = "parking")
	//private boolean parking;
	
	//@Column(name = "free_cancelation")
	//private boolean freeCancelation;
	
	@Column(name = "cost")
	private Float cost;
	
	//@OneToOne(mappedBy = "booking")
	//private Payment payment;
	
	
	public Booking() {
		super();
	}
	
	public Booking(User guest, Date checkIn, Date checkOut, Date createdAt, Room room, Float cost) {
		super();
		this.guest = guest;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.createdAt = createdAt;
		this.room = room;
		this.cost = cost;
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
	
	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
	}
	
}

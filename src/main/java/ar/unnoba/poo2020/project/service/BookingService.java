package ar.unnoba.poo2020.project.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.unnoba.poo2020.project.model.Booking;
import ar.unnoba.poo2020.project.model.Room;
import ar.unnoba.poo2020.project.repository.BookingRepository;
import ar.unnoba.poo2020.project.repository.RoomRepository;

@Service
public class BookingService implements IBookingService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	
	@Override
	public Booking createBooking(Booking booking) throws Exception {
		booking.setRoom(roomRepository.findById(booking.getRoom().getId()).get());
		booking.setCost(booking.getRoom().getPrice());
		booking.setCreatedAt(new Date());
		
		if (booking.getCheckIn().before(new Date()) || booking.getCheckIn().after(booking.getCheckOut())) {
			throw new Exception("");
		}
		
		Room r = roomRepository.isRoomAvailable(booking.getCheckIn(), booking.getCheckOut(), booking.getRoom().getId());
		
		if (r !=null) {
			return booking = bookingRepository.save(booking);
		} else {
			throw new Exception("");
		}
	}

}

package ar.unnoba.poo2020.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.unnoba.poo2020.project.dto.NewBookingRequestDTO;
import ar.unnoba.poo2020.project.dto.NewBookingResponseDTO;
import ar.unnoba.poo2020.project.dto.RoomDTO;
import ar.unnoba.poo2020.project.dto.RoomsAvailabilityDTO;
import ar.unnoba.poo2020.project.model.Booking;
import ar.unnoba.poo2020.project.model.Room;
import ar.unnoba.poo2020.project.model.User;
import ar.unnoba.poo2020.project.service.BookingService;
import ar.unnoba.poo2020.project.service.RoomService;

@Controller
@RequestMapping("/bookings")
public class BookingController {
	
	private RoomService roomService;
	private BookingService bookingService;
	private ModelMapper modelMapper;
	
	@Autowired
	public BookingController(RoomService roomService, ModelMapper modelMapper, BookingService bookingService) {
		this.bookingService = bookingService;
		this.roomService = roomService;
		this.modelMapper = modelMapper;
	} 
	
	@GetMapping("/availability")
	public String roomsAvailability(Model model) {
		model.addAttribute("roomsAvailability",new RoomsAvailabilityDTO());
		model.addAttribute("rooms", new ArrayList<RoomDTO>());
		return "bookings/availability";
	}
	
	@PostMapping("/availability")
	public String getRoomsAvailable(@ModelAttribute RoomsAvailabilityDTO roomsAvailabilityDTO, Model model) {
		List<Room> rooms = new ArrayList<>();
		try {
			rooms = roomService.getRoomsAvailable(
					roomsAvailabilityDTO.getCheckInDateConverted(),
					roomsAvailabilityDTO.getCheckOutDateConverted(),
					roomsAvailabilityDTO.getOccupancy());
			
		} catch (Exception e) {}
		
		List<RoomDTO> roomsDTO = rooms.stream()
				.map(room -> modelMapper.map(room, RoomDTO.class))
				.collect(Collectors.toList());
		
		model.addAttribute("rooms", roomsDTO);
		model.addAttribute("roomsAvailability", roomsAvailabilityDTO);
		model.addAttribute("bookings", new NewBookingRequestDTO());
		return "bookings/availability";
	}
	
	@PostMapping("/new")
	public String newBooking (@ModelAttribute NewBookingRequestDTO newBookingRequestDTO, Model model) {
		
		NewBookingResponseDTO booking = new NewBookingResponseDTO();
		RoomDTO roomDTO = modelMapper.map(roomService.findById(newBookingRequestDTO.getRoomId()).get(), RoomDTO.class);
		
		booking.setRoom(roomDTO);
		booking.setCheckIn(newBookingRequestDTO.getCheckIn());
		booking.setCheckOut(newBookingRequestDTO.getCheckOut());
		booking.setOccupancy(newBookingRequestDTO.getOccupancy());
		model.addAttribute("booking", booking);
		
		return "bookings/new";
	}
	
	@PostMapping("/confirm")
	public String createBooking(@ModelAttribute NewBookingRequestDTO confirmBooking, Authentication authentication, Model model) {
		
		//utilizo la clase ya creada NewBookingRequestDTO para no crear 2 clases iguales de gusto..
		
		Booking booking = modelMapper.map(confirmBooking, Booking.class);
		
		booking.setId(null); //porque sino copia el id de la habitación.
		booking.setGuest((User)authentication.getPrincipal());
		try {
			
			bookingService.createBooking(booking);
			return "bookings/confirmed";
		
		} catch (Exception e) {
			return "bookings/availability";
		}
		
	}
	
	@GetMapping("/confirm")
    public String bookingConfirmed(){
        return "bookings/confirmed";
    }
}

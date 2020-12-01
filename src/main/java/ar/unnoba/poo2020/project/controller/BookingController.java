package ar.unnoba.poo2020.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.unnoba.poo2020.project.dto.RoomDTO;
import ar.unnoba.poo2020.project.dto.RoomsAvailabilityDTO;
import ar.unnoba.poo2020.project.model.Room;
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
		return "bookings/availability";
	}
	
}

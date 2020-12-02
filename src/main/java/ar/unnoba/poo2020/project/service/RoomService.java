package ar.unnoba.poo2020.project.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.unnoba.poo2020.project.model.Room;
import ar.unnoba.poo2020.project.repository.RoomRepository;

@Service
public class RoomService implements IRoomService {
	
	private RoomRepository roomRepository;
	
	@Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

	@Override
	public List<Room> getRoomsAvailable(Date checkIn, Date checkOut, int occupancy) {
		return roomRepository.getRoomsAvailable(checkIn, checkOut, occupancy);
	}

	@Override
	public Optional<Room> findById(Long roomId) {
		return roomRepository.findById(roomId);
	}

	

}

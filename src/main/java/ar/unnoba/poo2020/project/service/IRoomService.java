package ar.unnoba.poo2020.project.service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import ar.unnoba.poo2020.project.model.Room;

public interface IRoomService {
	public List<Room> getRoomsAvailable(Date checkIn, Date checkOut, int occupancy);
	public Optional <Room> findById(Long roomId);
}

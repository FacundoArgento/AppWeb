package ar.unnoba.poo2020.project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.unnoba.poo2020.project.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
	@Query("Select r From Room r where r.occupancy >= :occupancy and r.availability > ("
			+ "Select count(b) From Booking b where b.room = r and b.checkIn between :checkIn and :checkOut)")
	List<Room> getRoomsAvailable(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut,
								@Param("occupancy") int occupancy);
	
}

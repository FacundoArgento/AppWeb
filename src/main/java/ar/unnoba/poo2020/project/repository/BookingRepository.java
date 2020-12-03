package ar.unnoba.poo2020.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.unnoba.poo2020.project.model.Booking;


public interface BookingRepository extends JpaRepository<Booking, Long> {

}

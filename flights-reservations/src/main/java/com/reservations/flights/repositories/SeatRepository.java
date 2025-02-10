package com.reservations.flights.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservations.flights.model.Seat;


public interface SeatRepository extends JpaRepository<Seat, Long> {
	
	Seat findByFlightIdAndNumberAndAvailableTrue(Long id, String number);
	
	List<Seat> findByFlightIdAndAvailableTrue(Long id);

}

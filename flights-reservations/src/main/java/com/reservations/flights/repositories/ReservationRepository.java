package com.reservations.flights.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservations.flights.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	Reservation findBySeatIdAndFlightIdAndCancelledTrue(Long seatId, Long flightId);
	
	List<Reservation> findByCancelledFalse();
	
	Reservation findByFlightOriginAndFlightDestinyAndSeatNumberAndCancelledTrueAndSeatAvailableTrue(String origin, String destiny, String seatNumber);
	
}

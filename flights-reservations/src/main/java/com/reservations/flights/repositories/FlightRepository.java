package com.reservations.flights.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservations.flights.model.Flight;


public interface FlightRepository extends JpaRepository<Flight, Long> {
	
	Flight findByOriginAndDestiny(String origin, String destiny);
}

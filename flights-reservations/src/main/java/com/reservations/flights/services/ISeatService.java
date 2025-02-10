package com.reservations.flights.services;

import java.util.List;

import com.reservations.flights.dto.SeatResponseDto;
import com.reservations.flights.model.Seat;


public interface ISeatService {
	
	List<Seat> findAll();

	List<Seat> createSeats(String origin, String destiny);

	Seat findByFlightIdAndNumberAndAvailableTrue(Long id, String number);
	
	SeatResponseDto findByFlightOriginAndFlightDestinyAndAvailableTrue(String origin, String destiny);

}

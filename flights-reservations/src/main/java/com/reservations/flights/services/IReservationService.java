package com.reservations.flights.services;

import java.util.List;
import java.util.Optional;

import com.reservations.flights.dto.ReservationResponseDto;
import com.reservations.flights.model.Reservation;

public interface IReservationService {

	List<Reservation> findAll();

	Optional<Reservation> findById(Long id);

	Optional<Reservation> save(Reservation reservation) ;

	Optional<Reservation> deleteById(Long id);
	
	List<ReservationResponseDto> findByCancelledFalse();
	
}

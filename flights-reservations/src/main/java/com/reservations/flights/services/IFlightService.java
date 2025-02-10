package com.reservations.flights.services;

import java.util.List;
import java.util.Optional;

import com.reservations.flights.dto.FlightRequestDto;
import com.reservations.flights.model.Flight;


public interface IFlightService {

	Flight findByOriginAndDestiny(String origin, String destiny);

	Optional<Flight> findById(Long id);

	Flight save(FlightRequestDto flight);

	List<Flight> findAll();

}

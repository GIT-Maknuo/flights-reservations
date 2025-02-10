package com.reservations.flights.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.reservations.flights.dto.SeatResponseDto;
import com.reservations.flights.model.Flight;
import com.reservations.flights.model.Seat;
import com.reservations.flights.repositories.FlightRepository;
import com.reservations.flights.repositories.SeatRepository;

@Service
public class SeatServiceImpl implements ISeatService {	

    private SeatRepository seatRepository;  
    
    private FlightRepository flightRepository;
    
    
    public SeatServiceImpl(SeatRepository seatRepository, FlightRepository flightRepository) {
		this.seatRepository = seatRepository;
		this.flightRepository = flightRepository;
	}


	@Override
	public Seat findByFlightIdAndNumberAndAvailableTrue(Long id, String number) {
		return seatRepository.findByFlightIdAndNumberAndAvailableTrue(id, number);
	}

	@Override
	public List<Seat> findAll() {
		return seatRepository.findAll();
	}

	
	public List<Seat> createSeats(String origin, String destiny) {
		List<Seat> seats = new ArrayList<>();
			Optional.ofNullable(flightRepository.findByOriginAndDestiny(origin.toUpperCase(), destiny.toUpperCase())).ifPresent(flight -> {
				for(int i = 1; i <= flight.getSeatsQuantity(); i++) {
					seats.add(new Seat("A"+i, true, flight));						
				}
			});
		return seats.isEmpty() ? seats : seatRepository.saveAll(seats);		
	}



	@Override
	public SeatResponseDto findByFlightOriginAndFlightDestinyAndAvailableTrue(String origin, String destiny) {
	    Flight flight = flightRepository.findByOriginAndDestiny(origin.toUpperCase(), destiny.toUpperCase());
	    if (flight == null) {
	        return null;
	    }	
		List<String> seats = seatRepository.findByFlightIdAndAvailableTrue(flight.getId()).stream().map(Seat::getNumber)
				.collect(Collectors.toList());
		return new SeatResponseDto(flight.getId(), flight.getOrigin(), flight.getDestiny(), seats);
	}



}

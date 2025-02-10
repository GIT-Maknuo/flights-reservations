package com.reservations.flights.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.reservations.flights.dto.FlightRequestDto;
import com.reservations.flights.model.Flight;
import com.reservations.flights.repositories.FlightRepository;



@Service
public class FlightServiceImpl implements IFlightService{
	
    private FlightRepository flightRepository;       

    FlightServiceImpl(FlightRepository flightRepository) {
		super();
		this.flightRepository = flightRepository;
	}

    @Override
	public Flight findByOriginAndDestiny(String origin, String destiny) {
        return flightRepository.findByOriginAndDestiny(origin, destiny);
    }

    @Override
    public Optional<Flight> findById(Long id) {
    	Optional<Flight> flightOp = flightRepository.findById(id);
    	return flightOp.isPresent() ? flightOp : Optional.empty(); 			
    }

	@Override
	public Flight save(FlightRequestDto flight) {
		Optional<Flight> flightOp = Optional.ofNullable(flightRepository
				.findByOriginAndDestiny(flight.origin().toUpperCase(), flight.destiny().toUpperCase()));		
		return flightOp.isPresent() ? 
				flightOp.get() 
				: flightRepository.save(new Flight(flight.origin().toUpperCase(), 
						flight.destiny().toUpperCase(), flight.seatsQuantity()));
	}

	@Override
	public List<Flight> findAll() {
		return flightRepository.findAll();
	}  

}

package com.reservations.flights.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservations.flights.dto.FlightRequestDto;
import com.reservations.flights.dto.FlightResponseDto;
import com.reservations.flights.model.Flight;
import com.reservations.flights.services.IFlightService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/flights")
@Tag(name = "Vuelos", description =  "Operaciones relacionadas con los vuelos")
public class FlightController {
	
	Logger log = LoggerFactory.getLogger(FlightController.class);

	
	private IFlightService flightService;

	public FlightController(IFlightService flightService) {
		super();
		this.flightService = flightService;
	}
	
	@GetMapping
    @Hidden
	public ResponseEntity<?> getFlights() {
		try {
			List<FlightResponseDto> response = flightService.findAll()
					.stream()
					.map(flght ->{
						return new FlightResponseDto(flght.getId(), flght.getOrigin(), flght.getDestiny());
					}).collect(Collectors.toList());		
			return response
					.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error("Error en Obtener los Flights " + e.getMessage());
			return ResponseEntity.noContent().build();		
		}

	}
	
	
	@GetMapping("{origin}/{destiny}")
    @Hidden
	public ResponseEntity<?> getFlight(@PathVariable String origin, @PathVariable String destiny) {
		try {
			Flight flight = flightService.findByOriginAndDestiny(origin.toUpperCase(), destiny.toUpperCase());
			return flight == null ? ResponseEntity.noContent().build() 
					: ResponseEntity.ok(new FlightResponseDto(flight.getId(), flight.getOrigin(), flight.getDestiny()));

		} catch (Exception e) {
			return ResponseEntity.noContent().build();		
		}
	}
	
	@PostMapping
	@Hidden
	public ResponseEntity<?> saveFlight(@RequestBody FlightRequestDto flight) {
		try {
			return Optional.of(flightService.save(flight))
					.map(flght ->{
						return ResponseEntity.ok(
								new FlightResponseDto(flght.getId(), flight.origin().toUpperCase(), 
										flight.destiny().toUpperCase()));
					}).orElseThrow();	
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error en la reservación");		}
	
	}
	

}

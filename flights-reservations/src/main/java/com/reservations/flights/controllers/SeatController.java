package com.reservations.flights.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservations.flights.dto.SeatResponseDto;
import com.reservations.flights.services.ISeatService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/seats")
@Tag(name = "Asientos", description= "Operaciones relacionadas con los asientos de los vuelos")
public class SeatController {
	
	Logger log = LoggerFactory.getLogger(SeatController.class);

	
	private ISeatService seatService;
	
	public SeatController(ISeatService seatService) {
		this.seatService = seatService;
	}
	
	
	@PutMapping("{origin}/{destiny}")
    @Hidden
	public ResponseEntity<?> createSeat(@PathVariable String origin, @PathVariable String destiny) {
		try {
			return ResponseEntity.ok(seatService.createSeats(origin, destiny)) ;

		} catch (Exception e) {
			log.error("Error en Obtener los asientos disponibles " + e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("{origin}/{destiny}")
	@Operation(summary = "Obtener los asientos disponibles", description = "Devuelve los asientos disponibles de un vuelo.")
	public ResponseEntity<?> getSeats(@Parameter(name = "Origen del vuelo", required = true)
									  @PathVariable String origin, 
									  @Parameter(name = "Destino del vuelo", required = true)
									  @PathVariable String destiny) {
		try {
			SeatResponseDto seats = seatService.findByFlightOriginAndFlightDestinyAndAvailableTrue(origin, destiny);
			return seats == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(seats);
		} catch (Exception e) {
			log.error("Error en Obtener los asientos disponibles " + e.getMessage());
			return ResponseEntity.noContent().build();
		}

	}


}

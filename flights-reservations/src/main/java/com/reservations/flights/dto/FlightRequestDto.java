package com.reservations.flights.dto;

public record FlightRequestDto(String origin, String destiny, int seatsQuantity) {
	public FlightRequestDto {
		if (origin == null || destiny == null || origin.isBlank() || destiny.isBlank() || seatsQuantity <= 0) {
			throw new IllegalArgumentException("All fields are required");
		}
	}
}

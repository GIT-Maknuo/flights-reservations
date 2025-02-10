package com.reservations.flights.dto;

public record ReservationRequestDto(String passengerName, String origen, String destino, String asiento) {
	public ReservationRequestDto {
		if (passengerName == null || origen == null || destino == null || asiento == null
			|| passengerName.isBlank() || origen.isBlank() || destino.isBlank() || asiento.isBlank()) {
			throw new IllegalArgumentException("All fields are required");
		}
	}

}

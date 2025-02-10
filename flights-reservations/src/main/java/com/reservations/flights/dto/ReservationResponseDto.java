package com.reservations.flights.dto;

public record ReservationResponseDto(String number, String passengerName, String origin, 
		String destiny, String seatNumber, String message) {
}

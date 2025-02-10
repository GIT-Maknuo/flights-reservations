package com.reservations.flights.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReservationResponseDtoTest {

    @Test
    void testValidReservationResponseDto() {
        ReservationResponseDto reservationResponse = new ReservationResponseDto("12345", "John Doe", "NYC", "LAX", "1A", "Your reservation is confirmed.");
        assertEquals("12345", reservationResponse.number());
        assertEquals("John Doe", reservationResponse.passengerName());
        assertEquals("NYC", reservationResponse.origin());
        assertEquals("LAX", reservationResponse.destiny());
        assertEquals("1A", reservationResponse.seatNumber());
        assertEquals("Your reservation is confirmed.", reservationResponse.message());
    }
}


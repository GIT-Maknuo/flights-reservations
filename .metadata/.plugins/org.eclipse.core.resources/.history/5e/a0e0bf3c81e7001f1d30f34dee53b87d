package com.reservations.flights.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SeatResponseDtoTest {

    @Test
    void testValidSeatResponseDto() {
        List<String> availableSeats = Arrays.asList("1A", "1B", "1C");
        SeatResponseDto seatResponse = new SeatResponseDto(12345L, "NYC", "LAX", availableSeats);
        
        assertEquals(12345L, seatResponse.numeroVuelo());
        assertEquals("NYC", seatResponse.origen());
        assertEquals("LAX", seatResponse.destino());
        assertEquals(availableSeats, seatResponse.asientosDisponibles());
    }
}


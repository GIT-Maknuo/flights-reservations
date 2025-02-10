package com.reservations.flights.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FlightResponseDtoTest {

    @Test
    void testFlightResponseDto() {
        Long numero = 1L;
        String origen = "NYC";
        String destino = "LAX";

        FlightResponseDto flightResponseDto = new FlightResponseDto(numero, origen, destino);

        assertEquals(numero, flightResponseDto.numero());
        assertEquals(origen, flightResponseDto.origen());
        assertEquals(destino, flightResponseDto.destino());
    }
}

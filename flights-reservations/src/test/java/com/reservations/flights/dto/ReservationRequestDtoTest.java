package com.reservations.flights.dto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReservationRequestDtoTest {

    @Test
    void testValidReservationRequestDto() {
        ReservationRequestDto reservationRequest = new ReservationRequestDto("John Doe", "NYC", "LAX", "1A");
        assertEquals("John Doe", reservationRequest.passengerName());
        assertEquals("NYC", reservationRequest.origen());
        assertEquals("LAX", reservationRequest.destino());
        assertEquals("1A", reservationRequest.asiento());
    }

    @Test
    void testInvalidReservationRequestDto_PassengerName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto(null, "NYC", "LAX", "1A");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto("", "NYC", "LAX", "1A");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto("  ", "NYC", "LAX", "1A");
        });
    }

    @Test
    void testInvalidReservationRequestDto_Origen() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto("John Doe", null, "LAX", "1A");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto("John Doe", "", "LAX", "1A");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto("John Doe", "  ", "LAX", "1A");
        });
    }

    @Test
    void testInvalidReservationRequestDto_Destino() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto("John Doe", "NYC", null, "1A");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto("John Doe", "NYC", "", "1A");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto("John Doe", "NYC", "  ", "1A");
        });
    }

    @Test
    void testInvalidReservationRequestDto_Asiento() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto("John Doe", "NYC", "LAX", null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto("John Doe", "NYC", "LAX", "");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ReservationRequestDto("John Doe", "NYC", "LAX", "  ");
        });
    }
}

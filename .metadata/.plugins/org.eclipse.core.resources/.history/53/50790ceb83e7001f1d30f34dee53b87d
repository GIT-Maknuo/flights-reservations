package com.reservations.flights.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SeatTest {

    @Test
    void testSeatConstructorAndGetters() {
        Flight flight = new Flight("NYC", "LAX", 200);
        Seat seat = new Seat("A1", true, flight);

        assertEquals("A1", seat.getNumber());
        assertTrue(seat.isAvailable());
        assertEquals(flight, seat.getFlight());
    }

    @Test
    void testSetters() {
        Flight flight = new Flight("NYC", "LAX", 200);
        Seat seat = new Seat();
        
        seat.setId(1L);
        seat.setNumber("A1");
        seat.setAvailable(true);
        seat.setFlight(flight);

        assertEquals(1L, seat.getId());
        assertEquals("A1", seat.getNumber());
        assertTrue(seat.isAvailable());
        assertEquals(flight, seat.getFlight());
    }

    @Test
    void testDefaultConstructor() {
        Seat seat = new Seat();
        assertNull(seat.getId());
        assertNull(seat.getNumber());
        assertFalse(seat.isAvailable());
        assertNull(seat.getFlight());
    }
}

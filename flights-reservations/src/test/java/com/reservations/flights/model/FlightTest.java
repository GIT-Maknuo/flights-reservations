package com.reservations.flights.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FlightTest {

    @Test
    void testFlightConstructorAndGetters() {
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat("A1", true, new Flight()));
        Flight flight = new Flight(1L, "NYC", "LAX", 200, seats);

        assertEquals(1L, flight.getId());
        assertEquals("NYC", flight.getOrigin());
        assertEquals("LAX", flight.getDestiny());
        assertEquals(200, flight.getSeatsQuantity());
        assertEquals(seats, flight.getSeats());
    }

    @Test
    void testSetters() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setOrigin("NYC");
        flight.setDestiny("LAX");
        flight.setSeatsQuantity(200);

        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat("A1", true, flight));
        flight.setSeats(seats);

        assertEquals(1L, flight.getId());
        assertEquals("NYC", flight.getOrigin());
        assertEquals("LAX", flight.getDestiny());
        assertEquals(200, flight.getSeatsQuantity());
        assertEquals(seats, flight.getSeats());
    }

    @Test
    void testDefaultConstructor() {
        Flight flight = new Flight();
        assertNull(flight.getId());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestiny());
        assertEquals(0, flight.getSeatsQuantity());
        assertNull(flight.getSeats());
    }
}

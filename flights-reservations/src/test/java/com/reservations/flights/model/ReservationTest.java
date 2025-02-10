package com.reservations.flights.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ReservationTest {

    @Test
    void testReservationConstructorAndGetters() {
        Flight flight = new Flight("NYC", "LAX", 200);
        Seat seat = new Seat("A1", true, flight);
        LocalDate date = LocalDate.now();
        Reservation reservation = new Reservation("John Doe", date, false, flight, seat);

        assertEquals("John Doe", reservation.getPassengerName());
        assertEquals(date, reservation.getDate());
        assertFalse(reservation.isCancelled());
        assertEquals(flight, reservation.getFlight());
        assertEquals(seat, reservation.getSeat());
    }

    @Test
    void testReservationConstructorWithDefaultDate() {
        Flight flight = new Flight("NYC", "LAX", 200);
        Seat seat = new Seat("A1", true, flight);
        Reservation reservation = new Reservation("John Doe", flight, seat);

        assertEquals("John Doe", reservation.getPassengerName());
        assertEquals(LocalDate.now(), reservation.getDate());
        assertFalse(reservation.isCancelled());
        assertEquals(flight, reservation.getFlight());
        assertEquals(seat, reservation.getSeat());
    }

    @Test
    void testSetters() {
        Reservation reservation = new Reservation();
        Flight flight = new Flight("NYC", "LAX", 200);
        Seat seat = new Seat("A1", true, flight);
        LocalDate date = LocalDate.now();
        
        reservation.setId(1L);
        reservation.setPassengerName("Jane Doe");
        reservation.setDate(date);
        reservation.setCancelled(true);
        reservation.setFlight(flight);
        reservation.setSeat(seat);

        assertEquals(1L, reservation.getId());
        assertEquals("Jane Doe", reservation.getPassengerName());
        assertEquals(date, reservation.getDate());
        assertTrue(reservation.isCancelled());
        assertEquals(flight, reservation.getFlight());
        assertEquals(seat, reservation.getSeat());
    }

    @Test
    void testDefaultConstructor() {
        Reservation reservation = new Reservation();
        assertNull(reservation.getId());
        assertNull(reservation.getPassengerName());
        assertNull(reservation.getDate());
        assertFalse(reservation.isCancelled());
        assertNull(reservation.getFlight());
        assertNull(reservation.getSeat());
    }
}


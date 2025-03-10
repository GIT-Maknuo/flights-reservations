package com.reservations.flights.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.reservations.flights.model.Flight;
import com.reservations.flights.model.Seat;

public class ReservationQDtoTest {

    @Test
    void testReservationQDto() {
        String passengerName = "John Doe";
        boolean cancelled = false;
        Flight flight = new Flight(1L, "NYC", "LAX", 10, null);
        Seat seat = new Seat("1A", true, flight);

        ReservationQDto reservationQDto = new ReservationQDto(passengerName, cancelled, flight, seat);

        assertEquals(passengerName, reservationQDto.passengerName());
        assertEquals(cancelled, reservationQDto.cancelled());
        assertEquals(flight, reservationQDto.flight());
        assertEquals(seat, reservationQDto.seat());
    }

    @Test
    void testReservationQDto_Cancelled() {
        String passengerName = "Jane Doe";
        boolean cancelled = true;
        Flight flight = new Flight(2L, "BOS", "MIA", 10, null);
        Seat seat = new Seat( "2B", true, flight);

        ReservationQDto reservationQDto = new ReservationQDto(passengerName, cancelled, flight, seat);

        assertEquals(passengerName, reservationQDto.passengerName());
        assertEquals(cancelled, reservationQDto.cancelled());
        assertEquals(flight, reservationQDto.flight());
        assertEquals(seat, reservationQDto.seat());
    }
}

package com.reservations.flights.dto;

import java.io.Serializable;

import com.reservations.flights.model.Flight;
import com.reservations.flights.model.Seat;

public record ReservationQDto(String passengerName, boolean cancelled,
		Flight flight, Seat seat) implements Serializable {

}

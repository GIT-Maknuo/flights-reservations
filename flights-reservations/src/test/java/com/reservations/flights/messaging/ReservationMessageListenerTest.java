package com.reservations.flights.messaging;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.reservations.flights.model.Reservation;
import com.reservations.flights.model.Seat;
import com.reservations.flights.model.Flight;
import com.reservations.flights.repositories.ReservationRepository;
import com.reservations.flights.repositories.SeatRepository;

@ExtendWith(MockitoExtension.class)
public class ReservationMessageListenerTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private ExecutorService executorService;

    @InjectMocks
    private ReservationMessageListener reservationMessageListener;



    @Test
    void testProcessReservation_ExistingReservation() {
        Reservation reservation = createTestReservation();
        Reservation existingReservation = createTestReservation();
        existingReservation.setCancelled(true);
        
        when(reservationRepository.findBySeatIdAndFlightIdAndCancelledTrue(anyLong(), anyLong()))
                .thenReturn(existingReservation);
        
        reservationMessageListener.processReservation(reservation);

        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verify(seatRepository, never()).save(any(Seat.class));
    }

    @Test
    void testProcessReservation_NewReservation() {
        Reservation reservation = createTestReservation();
        
        when(reservationRepository.findBySeatIdAndFlightIdAndCancelledTrue(anyLong(), anyLong()))
                .thenReturn(null);
        
        reservationMessageListener.processReservation(reservation);

        verify(seatRepository, times(1)).save(any(Seat.class));
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }
    
    private Reservation createTestReservation() {
        Flight flight = new Flight();
        flight.setId(1L);

        Seat seat = new Seat();
        seat.setId(1L);
        seat.setFlight(flight);
        
        Reservation reservation = new Reservation();
        reservation.setPassengerName("John Doe");
        reservation.setCancelled(false);
        reservation.setSeat(seat);
        reservation.setFlight(flight);
        reservation.setDate(LocalDate.now());

        return reservation;
    }
}

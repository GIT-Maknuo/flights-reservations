package com.reservations.flights.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.reservations.flights.config.RabbitMQConfig;
import com.reservations.flights.dto.ReservationResponseDto;
import com.reservations.flights.model.Reservation;
import com.reservations.flights.model.Seat;
import com.reservations.flights.model.Flight;
import com.reservations.flights.repositories.ReservationRepository;
import com.reservations.flights.services.ReservationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    void testFindAll() {
        List<Reservation> reservations = Arrays.asList(new Reservation(), new Reservation());
        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.findAll();
        assertEquals(reservations, result);
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testFindByCancelledFalse() {
        Reservation reservation = createTestReservation();
        List<Reservation> reservations = Arrays.asList(reservation);
        when(reservationRepository.findByCancelledFalse()).thenReturn(reservations);

        List<ReservationResponseDto> result = reservationService.findByCancelledFalse();
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).passengerName());
        assertEquals("NYC", result.get(0).origin());
        assertEquals("LAX", result.get(0).destiny());
        assertEquals("1A", result.get(0).seatNumber());
        assertEquals("Reserva activa", result.get(0).message());
        verify(reservationRepository, times(1)).findByCancelledFalse();
    }

    @Test
    void testFindById() {
        Reservation reservation = createTestReservation();
        reservation.setId(1L);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        Optional<Reservation> result = reservationService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(reservation, result.get());
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testSave_Success() {
        Reservation reservation = createTestReservation();

        Optional<Reservation> result = reservationService.save(reservation);
        assertTrue(result.isPresent());
        assertEquals(reservation, result.get());
        verify(rabbitTemplate, times(1)).convertAndSend(RabbitMQConfig.RESERVA_EXCHANGE, RabbitMQConfig.RESERVA_ROUTING_KEY, reservation);
    }

    @Test
    void testSave_Failure() {
        Reservation reservation = createTestReservation();
        doThrow(new RuntimeException()).when(rabbitTemplate).convertAndSend(anyString(), anyString(), any(Reservation.class));

        Optional<Reservation> result = reservationService.save(reservation);
        assertFalse(result.isPresent());
        verify(rabbitTemplate, times(1)).convertAndSend(RabbitMQConfig.RESERVA_EXCHANGE, RabbitMQConfig.RESERVA_ROUTING_KEY, reservation);
    }


    private Reservation createTestReservation() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setOrigin("NYC");
        flight.setDestiny("LAX");

        Seat seat = new Seat();
        seat.setId(1L);
        seat.setNumber("1A");
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

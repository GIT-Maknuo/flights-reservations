
package com.reservations.flights.messaging;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.reservations.flights.config.RabbitMQConfig;
import com.reservations.flights.model.Reservation;
import com.reservations.flights.repositories.ReservationRepository;
import com.reservations.flights.repositories.SeatRepository;



@Component
public class ReservationMessageListener {

    private final ReservationRepository reservationRepository;   
    private final SeatRepository seatRepository;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    
	ReservationMessageListener(ReservationRepository reservationRepository,
			SeatRepository seatRepository) {
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
        	
	}
    
    @RabbitListener(queues = RabbitMQConfig.RESERVA_QUEUE)
    public void receiveMessageReserv(Reservation reservation) {
        Future<?> future = executorService.submit(() -> processReservation(reservation));
        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Transactional
    public synchronized void processReservation(Reservation reservation) {
    	 Optional<Reservation> reservationOp = Optional.ofNullable(reservationRepository
 	            .findBySeatIdAndFlightIdAndCancelledTrue(reservation.getSeat().getId(), reservation.getFlight().getId()));

        reservationOp.ifPresentOrElse(reservationToUpdate -> {
            reservationToUpdate.setPassengerName(reservation.getPassengerName());
            reservationToUpdate.setCancelled(reservation.isCancelled());
            reservationToUpdate.getSeat().setAvailable(reservation.getSeat().isAvailable());
            reservationToUpdate.setDate(LocalDate.now());
            reservationRepository.save(reservationToUpdate);
        }, () -> {
        	      reservation.setDate(LocalDate.now());
        	      reservation.getSeat().setFlight(reservation.getFlight());
        	      seatRepository.save(reservation.getSeat());
        	      reservationRepository.save(reservation);
        	});
    }
    
}


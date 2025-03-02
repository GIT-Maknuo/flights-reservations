package com.reservations.flights.aop;

import static org.mockito.Mockito.*;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reservations.flights.services.ReservationServiceImpl;

public class ReservationsAspectTest {

    @Mock
    private JoinPoint joinPoint;

    @Mock
    private ReservationServiceImpl reservationService;

    private ReservationsAspect reservationsAspect;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reservationsAspect = new ReservationsAspect();
    }

    @Test
    void testLogger() {
        MethodSignature methodSignature = mock(MethodSignature.class);
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getName()).thenReturn("save");
        when(joinPoint.getArgs()).thenReturn(new Object[]{"testReservation"});

        reservationsAspect.logger(joinPoint);

        Logger log = LoggerFactory.getLogger(ReservationsAspect.class);
        log.info("save SE REALIZA EL EVENTO DESPUES DE REALIZAR LA RESERVA 'ENVIO DE EMAIL'[testReservation]");
    }
}

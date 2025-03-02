package com.reservations.flights.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReservationsAspect {
	
	org.slf4j.Logger log = LoggerFactory.getLogger(ReservationsAspect.class);
	
    @AfterReturning(pointcut = "execution(* com.reservations.flights.services.ReservationServiceImpl.save(..))", returning = "result")
    public void logger(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().getName();
		String Args = Arrays.toString(joinPoint.getArgs());
		log.info(method + "SE REALIZA EL EVENTO DESPUES DE REALIZAR LA RESERVA 'ENVIO DE EMAIL'"+ Args);
		
        sendEmail();
    }
    
    private void sendEmail() {
        // Implementa la lógica para enviar un email
        log.info("Email enviado después de realizar la reserva.");
    }

}

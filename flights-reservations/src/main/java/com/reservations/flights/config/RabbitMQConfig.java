
package com.reservations.flights.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reservations.flights.model.Reservation;


@Configuration
public class RabbitMQConfig {
    public static final String RESERVA_QUEUE = "reservaQueue";
    public static final String RESERVA_EXCHANGE = "reservaExchange";
    public static final String RESERVA_ROUTING_KEY = "reservaRoutingKey";

    @Bean
    Queue reservaQueue() {
        return new Queue(RESERVA_QUEUE, true);
    }

    @Bean
    DirectExchange reservaExchange() {
        return new DirectExchange(RESERVA_EXCHANGE);
    }

    @Bean
    Binding reservaBinding(Queue reservaQueue, DirectExchange reservaExchange) {
        return BindingBuilder.bind(reservaQueue).to(reservaExchange).with(RESERVA_ROUTING_KEY);
    }
    
    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("com.flight.reservations.entities", Reservation.class);
        typeMapper.setIdClassMapping(idClassMapping);
        converter.setJavaTypeMapper(typeMapper);
        return converter;
    }

    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }
    
}

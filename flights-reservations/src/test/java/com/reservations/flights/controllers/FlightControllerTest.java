package com.reservations.flights.controllers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reservations.flights.dto.FlightRequestDto;
import com.reservations.flights.dto.FlightResponseDto;
import com.reservations.flights.model.Flight;
import com.reservations.flights.services.IFlightService;

@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFlightService flightService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser(username = "freddy", password = "abc123", roles = {"ADMIN"})
    void testGetFlights() throws Exception {
        List<Flight> flights = Arrays.asList(
                new Flight(1L, "NYC", "LAX", 10, new ArrayList<>()),
                new Flight(2L, "BOS", "MIA", 10, new ArrayList<>())
        );
        List<FlightResponseDto> response = Arrays.asList(
                new FlightResponseDto(1L, "NYC", "LAX"),
                new FlightResponseDto(2L, "BOS", "MIA")
        );

        when(flightService.findAll()).thenReturn(flights);

        mockMvc.perform(get("/api/flights"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    @WithMockUser(username = "freddy", roles = {"ADMIN"})
    void testGetFlight() throws Exception {
        Flight flight = new Flight(1L, "NYC", "LAX", 10, new ArrayList<>());
        FlightResponseDto response = new FlightResponseDto(1L, "NYC", "LAX");

        when(flightService.findByOriginAndDestiny("NYC", "LAX")).thenReturn(flight);

        mockMvc.perform(get("/api/flights/NYC/LAX"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    @WithMockUser(username = "freddy", password = "abc123", roles = {"ADMIN"})
    void testSaveFlight() throws Exception {
        FlightRequestDto request = new FlightRequestDto("NYC", "LAX", 10);
        Flight flight = new Flight(1L, "NYC", "LAX", 10, new ArrayList<>());
        FlightResponseDto response = new FlightResponseDto(1L, "NYC", "LAX");

        when(flightService.save(request)).thenReturn(flight);

        mockMvc.perform(post("/api/flights")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "freddy", roles = {"ADMIN"})
    void testGetFlights_NoContent() throws Exception {
        when(flightService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/flights"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "freddy", roles = {"ADMIN"})
    void testGetFlight_NoContent() throws Exception {
        when(flightService.findByOriginAndDestiny("NYC", "LAX")).thenReturn(null);

        mockMvc.perform(get("/api/flights/NYC/LAX"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "freddy", password = "abc123", roles = {"ADMIN"})
    void testSaveFlight_BadRequest() throws Exception {
        FlightRequestDto request = new FlightRequestDto("NYC", "LAX", 10);

        when(flightService.save(request)).thenThrow(new RuntimeException("Error en la reservación"));

        mockMvc.perform(post("/api/flights")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }
}

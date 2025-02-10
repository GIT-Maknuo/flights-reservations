package com.reservations.flights.dto;

import java.util.List;

public record SeatResponseDto(Long numeroVuelo, String origen, String destino, List<String> asientosDisponibles) {


}

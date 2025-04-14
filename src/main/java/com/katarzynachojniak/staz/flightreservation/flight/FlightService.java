package com.katarzynachojniak.staz.flightreservation.flight;

import java.util.List;

public interface FlightService {

    FlightDto createFlight(FlightDto flightDto);
    FlightDto updateFlight(long id, FlightDto flightDto);
    void deleteFlight(Long id);

    List<FlightDto> getAllFlights();
    FlightDto getFlightDtoById(Long id);
    Flight getFlightById(Long id);

}

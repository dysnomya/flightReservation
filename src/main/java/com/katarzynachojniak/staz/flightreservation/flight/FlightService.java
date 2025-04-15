package com.katarzynachojniak.staz.flightreservation.flight;

import java.util.List;

public interface FlightService {

    FlightDto createFlight(FlightDto flightDto);
    FlightDto updateFlight(String flightNumber, FlightDto flightDto);
    void deleteFlight(String flightNumber);

    List<FlightDto> getAllFlights();
    FlightDto getFlightDtoByFlightNumber(String flightNumber);
    Flight getFlightByFlightNumber(String flightNumber);

}

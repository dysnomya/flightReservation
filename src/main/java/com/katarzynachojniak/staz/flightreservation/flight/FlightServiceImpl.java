package com.katarzynachojniak.staz.flightreservation.flight;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;


    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public FlightDto createFlight(FlightDto flightDto) {
        Flight flight = flightMapper.toEntity(flightDto);
        Flight savedFlight = flightRepository.save(flight);

        return flightMapper.toDto(savedFlight);
    }

    @Override
    public FlightDto updateFlight(String flightNumber, FlightDto dto) {

        Flight existingFlight = getFlightByFlightNumber(flightNumber);

        flightMapper.partialUpdate(dto, existingFlight);

        Flight savedFlight = flightRepository.save(existingFlight);
        return flightMapper.toDto(savedFlight);
    }

    @Override
    public void deleteFlight(String flightNumber) {
        flightRepository.deleteByFlightNumber(flightNumber);
    }

    @Override
    public List<FlightDto> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flightMapper.toDto(flights);
    }

    @Override
    public FlightDto getFlightDtoByFlightNumber(String flightNumber) {
        Flight flight = getFlightByFlightNumber(flightNumber);

        return flightMapper.toDto(flight);
    }

    @Override
    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightRepository.getByFlightNumber(flightNumber);
    }
}

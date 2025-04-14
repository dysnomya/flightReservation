package com.katarzynachojniak.staz.flightreservation.flight;

import com.katarzynachojniak.staz.flightreservation.seat.Seat;
import com.katarzynachojniak.staz.flightreservation.seat.SeatMapper;
import com.katarzynachojniak.staz.flightreservation.seat.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public FlightDto updateFlight(long id, FlightDto dto) {

        Flight existingFlight = flightRepository.findById(id).orElse(null);

        if (existingFlight == null) {
            return null;
        }

        flightMapper.partialUpdate(dto, existingFlight);

        Flight savedFlight = flightRepository.save(existingFlight);
        return flightMapper.toDto(savedFlight);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightDto> getAllFlights() {
        List<Flight> flights = (List<Flight>) flightRepository.findAll();
        return flightMapper.toDto(flights);
    }

    @Override
    public FlightDto getFlightDtoById(Long id) {
        Flight flight = getFlightById(id);

        return flightMapper.toDto(flight);
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }
}

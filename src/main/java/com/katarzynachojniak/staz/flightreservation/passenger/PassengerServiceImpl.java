package com.katarzynachojniak.staz.flightreservation.passenger;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.flight.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;
    private final FlightRepository flightRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository, PassengerMapper passengerMapper, FlightRepository flightRepository) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
        this.flightRepository = flightRepository;
    }

    @Override
    public PassengerDto createPassenger(PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.toEntity(passengerDto);
        Passenger savedPassenger = passengerRepository.save(passenger);

        return passengerMapper.toDto(savedPassenger);
    }

    @Override
    public PassengerDto getPassengerById(Long id) {
        Passenger passenger = passengerRepository.findById(id).orElse(null);

        return passengerMapper.toDto(passenger);
    }

    @Override
    public List<PassengerDto> getAllPassengers() {
        List<Passenger> passengers = (List<Passenger>) passengerRepository.findAll();

        return passengerMapper.toDto(passengers);
    }

    @Override
    public PassengerDto updatePassenger(Long id, PassengerDto passengerDto) {
        Passenger existingPassenger = passengerRepository.findById(id).orElse(null);

        if (existingPassenger == null) {
            return null;
        }

        passengerMapper.partialUpdate(passengerDto, existingPassenger);

        Passenger savedPassenger = passengerRepository.save(existingPassenger);
        return passengerMapper.toDto(savedPassenger);
    }

    @Override
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}

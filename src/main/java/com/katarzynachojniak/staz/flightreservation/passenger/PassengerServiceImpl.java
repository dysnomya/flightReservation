package com.katarzynachojniak.staz.flightreservation.passenger;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    public PassengerServiceImpl(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public PassengerDto createPassenger(PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.toEntity(passengerDto);
        Passenger savedPassenger = passengerRepository.save(passenger);

        return passengerMapper.toDto(savedPassenger);
    }

    @Override
    public PassengerDto getPassengerDtoById(Long id) {
        Passenger passenger = getPassengerById(id);

        return passengerMapper.toDto(passenger);
    }

    @Override
    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id).orElse(null);
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

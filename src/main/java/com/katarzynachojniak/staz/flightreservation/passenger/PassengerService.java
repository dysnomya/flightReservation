package com.katarzynachojniak.staz.flightreservation.passenger;

import java.util.List;

public interface PassengerService {

    PassengerDto createPassenger(PassengerDto passengerDto);
    PassengerDto getPassengerById(Long id);
    List<PassengerDto> getAllPassengers();
    PassengerDto updatePassenger(Long id, PassengerDto passengerDto);
    void deletePassenger(Long id);
}

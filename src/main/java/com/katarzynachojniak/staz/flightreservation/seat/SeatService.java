package com.katarzynachojniak.staz.flightreservation.seat;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.flight.FlightDto;

public interface SeatService {

    SeatDto getSeatById(Long id);

    Seat getSeatBySeatNumberAndFlight(String seatNumber, Flight flight);
}

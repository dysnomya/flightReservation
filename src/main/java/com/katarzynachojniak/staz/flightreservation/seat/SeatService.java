package com.katarzynachojniak.staz.flightreservation.seat;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.flight.FlightDto;
import org.springframework.stereotype.Service;

public interface SeatService {

    SeatDto getSeatById(Long id);

    Seat getSeatBySeatNumberAndFlight(String seatNumber, Flight flight);

    Seat save(Seat seat);
}

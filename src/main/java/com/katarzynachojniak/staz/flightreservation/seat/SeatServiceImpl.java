package com.katarzynachojniak.staz.flightreservation.seat;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.flight.FlightDto;
import com.katarzynachojniak.staz.flightreservation.flight.FlightMapper;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    private final FlightMapper flightMapper;

    public SeatServiceImpl(SeatRepository seatRepository, SeatMapper seatMapper, FlightMapper flightMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
        this.flightMapper = flightMapper;
    }


    @Override
    public SeatDto getSeatById(Long id) {
        Seat seat = seatRepository.findById(id).orElse(null);

        return seatMapper.toDto(seat);
    }

    @Override
    public Seat getSeatBySeatNumberAndFlight(String seatNumber, Flight flight) {

        return seatRepository.findSeatBySeatNumberAndFlight(seatNumber, flight);
    }
}

package com.katarzynachojniak.staz.flightreservation.seat;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.flight.FlightDto;
import com.katarzynachojniak.staz.flightreservation.flight.FlightMapper;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link SeatService} interface, responsible for seat-related business logic.
 *
 * <p>This service interacts with the {@link SeatRepository} for data access and uses {@link SeatMapper}
 * to convert between entity and DTO representations.</p>
 */
@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    /**
     * Constructs a new SeatServiceImpl with required dependencies.
     *
     * @param seatRepository repository for seat persistence operations
     * @param seatMapper     mapper for converting between Seat and SeatDto
     */
    public SeatServiceImpl(SeatRepository seatRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    /**
     * Retrieves a seat by its ID and returns a DTO representation.
     *
     * @param id the ID of the seat
     * @return the corresponding {@link SeatDto}, or {@code null} if not found
     */
    @Override
    public SeatDto getSeatById(Long id) {
        Seat seat = seatRepository.findById(id).orElse(null);

        return seatMapper.toDto(seat);
    }

    /**
     * Finds a seat by seat number and flight.
     *
     * @param seatNumber the number of the seat (e.g., "12A")
     * @param flight     the flight associated with the seat
     * @return the matching {@link Seat}, or {@code null} if none is found
     */
    @Override
    public Seat getSeatBySeatNumberAndFlight(String seatNumber, Flight flight) {

        return seatRepository.findSeatBySeatNumberAndFlight(seatNumber, flight);
    }

    /**
     * Saves or updates a seat in the database.
     *
     * @param seat the seat to be saved
     * @return the persisted {@link Seat} entity
     */
    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Seat setFlightForSeat(Seat seat, Flight flight) {
        seat.setFlight(flight);
        seatRepository.save(seat);

        return seat;
    }
}

package com.katarzynachojniak.staz.flightreservation.seat;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.flight.FlightDto;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing {@link Seat} entities.
 *
 * <p>This interface defines operations for accessing and manipulating seat data used within the flight reservation system.</p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *     <li>Retrieve seat details by ID</li>
 *     <li>Find a seat by seat number and associated flight</li>
 *     <li>Persist new or updated seat data</li>
 * </ul>
 */
public interface SeatService {

    /**
     * Retrieves a seat DTO by its ID.
     *
     * @param id the ID of the seat
     * @return a {@link SeatDto} representing the seat
     */
    SeatDto getSeatById(Long id);

    /**
     * Finds a seat by its seat number and associated flight.
     *
     * @param seatNumber the number of the seat (e.g., "12A")
     * @param flight the flight the seat belongs to
     * @return the corresponding {@link Seat} entity
     */
    Seat getSeatBySeatNumberAndFlight(String seatNumber, Flight flight);

    /**
     * Saves or updates a seat entity in the database.
     *
     * @param seat the seat to persist
     * @return the saved {@link Seat} entity
     */
    Seat save(Seat seat);
}

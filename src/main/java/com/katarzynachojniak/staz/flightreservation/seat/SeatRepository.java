package com.katarzynachojniak.staz.flightreservation.seat;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Seat} entities.
 * Extends Spring Data's {@link CrudRepository} to provide basic CRUD operations.
 */
public interface SeatRepository extends CrudRepository<Seat, Long> {

  List<Seat> findSeatBySeatNumber(String seatNumber);

  Seat findSeatBySeatNumberAndFlight(String seatNumber, Flight flight);
}
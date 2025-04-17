package com.katarzynachojniak.staz.flightreservation.flight;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Flight} entities.
 *
 * <p>Extends Spring Data's {@link CrudRepository} to provide basic CRUD operations.
 * Also includes custom query methods based on flight number.</p>
 */
@Repository
public interface FlightRepository extends CrudRepository<Flight, String> {

    /**
     * Deletes a flight by its flight number.
     *
     * @param flightNumber the flight number of the flight to delete
     */
    void deleteByFlightNumber(String flightNumber);
}
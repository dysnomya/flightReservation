package com.katarzynachojniak.staz.flightreservation.passenger;

import com.katarzynachojniak.staz.flightreservation.flight.FlightDto;

import java.util.List;

/**
 * Service interface for handling passenger-related operations.
 *
 * <p>Provides business logic for managing passengers, including operations like:</p>
 * <ul>
 *     <li>Creating and updating new passengers</li>
 *     <li>Retrieving data of all passengers</li>
 *     <li>Retrieving passengers by their id</li>
 *     <li>Deleting passengers</li>
 * </ul>
 *
 * <p>This interface defines the core operations but does not contain the actual logic.
 * Implementations should provide specific behavior.</p>
 */
public interface PassengerService {

    /**
     * Retrieves all available passengers.
     *
     * @return a list of all available passengers as {@link PassengerDto} object
     */
    List<PassengerDto> getAllPassengers();

    /**
     * Creates a new passenger
     * @param passengerDto the passenger data to be created
     * @return the created passenger as {@link PassengerDto}
     */
    PassengerDto createPassenger(PassengerDto passengerDto);

    /**
     * Retrieves passenger details by passenger's id
     * @param id the unique identifier of a passenger
     * @return corresponding passenger as a {@link PassengerDto}, or {@code null} if no passenger is found with given id
     */
    PassengerDto getPassengerDtoById(Long id);


    /**
     * Retrieves passenger details by passenger's id
     * @param id the unique identifier of a passenger
     * @return corresponding passenger as {@link Passenger} entity, or {@code null} if no passenger is found with given id
     */
    Passenger getPassengerById(Long id);

    /**
     * Updates an existing passenger
     * @param id the unique identifier of a passenger
     * @param passengerDto the new passenger data
     * @return as a {@link PassengerDto}, or {@code null} if no passenger is found with the given id
     */
    PassengerDto updatePassenger(Long id, PassengerDto passengerDto);

    /**
     * Deletes a passenger by passenger's id
     * @param id the unique identifier of a passenger to be deleted
     */
    void deletePassenger(Long id);
}

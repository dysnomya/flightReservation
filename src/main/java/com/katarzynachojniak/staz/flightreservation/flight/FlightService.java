package com.katarzynachojniak.staz.flightreservation.flight;

import com.katarzynachojniak.staz.flightreservation.seat.SeatDto;

import java.util.List;

/**
 * Service interface for handling flight-related operations.
 *
 * <p>Provides business logic for managing flights, including operations like:</p>
 * <ul>
 *     <li>Creating and updating new flights</li>
 *     <li>Retrieving all flights</li>
 *     <li>Retrieving flights by flight number</li>
 *     <li>Deleting flights</li>
 * </ul>
 *
 * <p>This interface defines the core operations but does not contain the actual logic.
 * Implementations should provide specific behavior.</p>
 */
 public interface FlightService {

    /**
     * Retrieves all available flights.
     *
     * @return a list of all available flights as {@link FlightDto} object
     */
    List<FlightDto> getAllFlights();

    /**
     * Creates a new flight.
     *
     * @param flightDto the flight data to be created
     * @return the created flight as a {@link FlightDto}
     */
    FlightDto createFlight(FlightDto flightDto);

    /**
     * Retrieves a flight's details by its flight number.
     *
     * @param flightNumber the unique identifier for the flight
     * @return the corresponding flight as a {@link FlightDto}, or {@code null} if no flight is found with the given flight number
     */
    FlightDto getFlightDtoByFlightNumber(String flightNumber);

    /**
     * Retrieves a flight by its flight number.
     *
     * @param flightNumber the unique identifier for the flight
     * @return the corresponding {@link Flight} entity, or {@code null} if no flight is found with the given flight number
     */
    Flight getFlightByFlightNumber(String flightNumber);

    /**
     * Updates an existing flight.
     *
     * @param flightNumber the flight number of the flight to update
     * @param flightDto the new flight data
     * @return as a {@link FlightDto}, or {@code null} if no flight is found with the given flight number
     */
    FlightDto updateFlight(String flightNumber, FlightDto flightDto);

    /**
     * Deletes a flight by its flight number.
     *
     * @param flightNumber the flight number of the flight to delete
     */
    void deleteFlight(String flightNumber);

   /**
    * Adds a seat to a flight by its flight number
    * @param flightNumber the flight number of the flight to add a seat to
    * @param seatDto seat DTO of a seat to add to flight
    * @return as a {@link FlightDto}, or {@code null} if no flight is found with the given flight number
    */
    FlightDto addSeat(String flightNumber, SeatDto seatDto);

    /**
     * Removes a seat from a flight
     * @param flightNumber the flight number of the flight to remove seat from
     * @param seatDto seat DTO of a seat to add to flight
     * @return as a {@link FlightDto}, or {@code null} if no flight is found with the given flight number
     */
   FlightDto removeSeat(String flightNumber, SeatDto seatDto);
}

package com.katarzynachojniak.staz.flightreservation.reservation;

import java.util.List;

/**
 * Service interface for handling reservation-related operations.
 *
 * <p>Provides business logic for managing flight reservations, including:</p>
 * <ul>
 *     <li>Creating and updating reservations</li>
 *     <li>Retrieving all reservations</li>
 *     <li>Fetching a reservation by ID</li>
 *     <li>Deleting reservations</li>
 * </ul>
 *
 * <p>This interface defines the core operations but does not contain the actual logic.
 * Implementations should provide specific behavior.</p>
 */
public interface ReservationService {

    /**
     * Creates a new reservation.
     *
     * @param reservationCreateDto the reservation data to be created
     * @return the created reservation as a {@link ReservationDto}
     */
    ReservationDto createReservation(ReservationCreateDto reservationCreateDto);

    /**
     * Retrieves a reservation's details by its ID.
     *
     * @param id the unique identifier of the reservation
     * @return the corresponding reservation as a {@link ReservationDto}, or {@code null} if not found
     */
    ReservationDto getReservationDtoById(Long id);

    /**
     * Retrieves a reservation entity by its ID.
     *
     * @param id the unique identifier of the reservation
     * @return the corresponding {@link Reservation} entity, or {@code null} if not found
     */
    Reservation getReservationById(Long id);

    /**
     * Retrieves all reservations.
     *
     * @return a list of all reservations as {@link ReservationDto} objects
     */
    List<ReservationDto> getAllReservations();

    /**
     * Updates an existing reservation.
     *
     * @param id the ID of the reservation to update
     * @param dto the new reservation data
     * @return the updated reservation as a {@link ReservationDto}, or {@code null} if not found
     */
    ReservationDto updateReservation(Long id, ReservationCreateDto dto);

    /**
     * Deletes a reservation by its ID.
     *
     * @param id the ID of the reservation to delete
     */
    void deleteReservation(Long id);

    /**
     * Mark all reservations for a given flight as "departed".
     * @param flightNumber flight number to set as "departed"
     */
    List<ReservationDto> markReservationsAsDeparted(String flightNumber);
}

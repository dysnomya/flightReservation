package com.katarzynachojniak.staz.flightreservation.reservation;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing {@link Reservation} entities.
 *
 * <p>Extends Spring Data's {@link CrudRepository} to provide basic CRUD operations.</p>
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
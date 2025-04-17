package com.katarzynachojniak.staz.flightreservation.passenger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Passenger} entities.
 *
 * <p>Extends Spring Data's {@link CrudRepository} to provide basic CRUD operations.</p>
 */
@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Long> {

  Passenger getById(Long id);
}
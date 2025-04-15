package com.katarzynachojniak.staz.flightreservation.passenger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Long> {
  Passenger getPassengerById(Long id);

  Passenger getById(Long id);
//  Passenger findById(Long id);
}
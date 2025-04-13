package com.katarzynachojniak.staz.flightreservation.passenger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends PagingAndSortingRepository<Passenger, Long>, CrudRepository<Passenger, Long> {
  Passenger getPassengerById(Long id);
//  Passenger findById(Long id);
}
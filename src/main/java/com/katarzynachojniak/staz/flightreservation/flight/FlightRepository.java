package com.katarzynachojniak.staz.flightreservation.flight;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlightRepository extends CrudRepository<Flight, String> {

    void deleteByFlightNumber(String flightNumber);

    @Override
    List<Flight> findAll();

    Flight findFlightByFlightNumber(String flightNumber);
}
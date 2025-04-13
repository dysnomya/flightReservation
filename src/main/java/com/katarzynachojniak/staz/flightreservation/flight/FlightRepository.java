package com.katarzynachojniak.staz.flightreservation.flight;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlightRepository extends PagingAndSortingRepository<Flight, Long>, CrudRepository<Flight, Long> {
//    Flight findById(long id);
    List<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findByDeparturePlace(String departurePlace);
    List<Flight> findByDeparturePlaceAndArrivalPlace(String departurePlace, String arrivalPlace);
}
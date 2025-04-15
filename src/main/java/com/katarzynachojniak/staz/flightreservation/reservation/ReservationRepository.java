package com.katarzynachojniak.staz.flightreservation.reservation;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> getById(Long id);
}
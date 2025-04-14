package com.katarzynachojniak.staz.flightreservation.reservation;

import java.util.List;

public interface ReservationService {
    ReservationDto createReservation(ReservationCreateDto reservationCreateDto);
    ReservationDto getReservationById(Long id);
    List<ReservationDto> getAllReservations();
    ReservationDto updateReservation(Long id, ReservationDto reservationDto);
    void deleteReservation(Long id);
}

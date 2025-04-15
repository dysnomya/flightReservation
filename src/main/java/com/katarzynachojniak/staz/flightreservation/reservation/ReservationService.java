package com.katarzynachojniak.staz.flightreservation.reservation;

import java.util.List;

public interface ReservationService {
    ReservationDto createReservation(ReservationCreateDto reservationCreateDto);
    ReservationDto getReservationDtoById(Long id);
    Reservation getReservationById(Long id);
    List<ReservationDto> getAllReservations();
    ReservationDto updateReservation(Long id, ReservationCreateDto dto);
    void deleteReservation(Long id);
}

package com.katarzynachojniak.staz.flightreservation.reservation;

import com.katarzynachojniak.staz.flightreservation.flight.FlightMapper;
import com.katarzynachojniak.staz.flightreservation.passenger.PassengerMapper;
import com.katarzynachojniak.staz.flightreservation.seat.SeatMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {FlightMapper.class, SeatMapper.class, PassengerMapper.class})
public interface ReservationMapper {
    Reservation toEntity(ReservationDto reservationDto);

    ReservationDto toDto(Reservation reservation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Reservation partialUpdate(ReservationDto reservationDto, @MappingTarget Reservation reservation);


    List<Reservation> toEntity(List<ReservationDto> reservationDto);

    List<ReservationDto> toDto(List<Reservation> reservation);
}
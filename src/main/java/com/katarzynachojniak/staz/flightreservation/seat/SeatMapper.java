package com.katarzynachojniak.staz.flightreservation.seat;

import org.mapstruct.*;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SeatMapper {
    Seat toEntity(SeatDto seatDto);

    SeatDto toDto(Seat seat);

    Set<Seat> toEntity(Set<SeatDto> seatDto);

    Set<SeatDto> toDto(Set<Seat> seat);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Seat partialUpdate(SeatDto seatDto, @MappingTarget Seat seat);

}
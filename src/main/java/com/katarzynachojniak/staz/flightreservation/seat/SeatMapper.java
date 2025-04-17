package com.katarzynachojniak.staz.flightreservation.seat;

import org.mapstruct.*;

import java.util.Set;

/**
 * Mapper for converting between {@link Seat} and {@link SeatDto}
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SeatMapper {
    Seat toEntity(SeatDto seatDto);

    SeatDto toDto(Seat seat);

    Set<Seat> toEntity(Set<SeatDto> seatDto);

    Set<SeatDto> toDto(Set<Seat> seat);

    /**
     * Partially updates an existing seat.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Seat partialUpdate(SeatDto seatDto, @MappingTarget Seat seat);

}
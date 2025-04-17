package com.katarzynachojniak.staz.flightreservation.passenger;

import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for converting between {@link Passenger} and {@link PassengerDto}.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PassengerMapper {

    Passenger toEntity(PassengerDto passengerDto);

    PassengerDto toDto(Passenger passenger);

    /**
     * Partially updates an existing passenger entity with non-null values from FlightDto.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Passenger partialUpdate(PassengerDto passengerDto, @MappingTarget Passenger passenger);

    List<Passenger> toEntity(List<PassengerDto> passengerDto);

    List<PassengerDto> toDto(List<Passenger> passenger);
}
package com.katarzynachojniak.staz.flightreservation.passenger;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PassengerMapper {
    Passenger toEntity(PassengerDto passengerDto);

    PassengerDto toDto(Passenger passenger);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Passenger partialUpdate(PassengerDto passengerDto, @MappingTarget Passenger passenger);


    List<Passenger> toEntity(List<PassengerDto> passengerDto);

    List<PassengerDto> toDto(List<Passenger> passenger);
}
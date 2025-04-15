package com.katarzynachojniak.staz.flightreservation.flight;

import com.katarzynachojniak.staz.flightreservation.seat.SeatMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {SeatMapper.class})
public interface FlightMapper {

    @Mapping(target = "flightNumber")
    Flight toEntity(FlightDto flightDto);

    @AfterMapping
    default void linkSeats(@MappingTarget Flight flight) {
        flight.getSeats().forEach(seat -> seat.setFlight(flight));
    }

    @Mapping(target = "flightNumber")
    FlightDto toDto(Flight flight);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Flight partialUpdate(FlightDto flightDto, @MappingTarget Flight flight);

    List<Flight> toEntity(List<FlightDto> flightDto);

    List<FlightDto> toDto(List<Flight> flight);

}
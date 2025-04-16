package com.katarzynachojniak.staz.flightreservation.flight;

import com.katarzynachojniak.staz.flightreservation.seat.SeatMapper;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for converting between Flight and FlightDto.
 * <p>
 * Uses SeatService to resolve Seat entities from seat numbers.
 * Also links seats to their parent flight after mapping (see {@link #linkSeats(Flight)}).
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {SeatMapper.class})
public interface FlightMapper {

    /**
     * Maps a FlightDto to a Flight entity.
     * Also links seats to the resulting flight entity after mapping.
     */
    @Mapping(source = "from", target = "departureLocation")
    @Mapping(source = "to", target = "arrivalLocation")
    Flight toEntity(FlightDto flightDto);

    @InheritInverseConfiguration(name = "toEntity")
    FlightDto toDto(Flight flight);

    /**
     * Populates the 'flight' field in each Seat after mapping.
     */
    @AfterMapping
    default void linkSeats(@MappingTarget Flight flight) {
        flight.getSeats().forEach(seat -> seat.setFlight(flight));
    }

    /**
     * Partially updates an existing Flight entity with non-null values from FlightDto.
     */
    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Flight partialUpdate(FlightDto flightDto, @MappingTarget Flight flight);

    @InheritConfiguration(name = "toEntity")
    List<Flight> toEntity(List<FlightDto> flightDto);

    @InheritInverseConfiguration(name = "toEntity")
    List<FlightDto> toDto(List<Flight> flight);

}
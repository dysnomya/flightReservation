    package com.katarzynachojniak.staz.flightreservation.reservation;

    import com.katarzynachojniak.staz.flightreservation.flight.FlightMapper;
    import com.katarzynachojniak.staz.flightreservation.passenger.PassengerMapper;
    import com.katarzynachojniak.staz.flightreservation.seat.SeatMapper;
    import org.mapstruct.*;

    import java.util.List;

    /**
     * Mapper for converting between {@link Reservation} and {@link ReservationDto}
     */
    @Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {FlightMapper.class, SeatMapper.class, PassengerMapper.class})
    public interface ReservationMapper {

        @Mapping(source = "seatNumber", target = "seat.seatNumber")
        @Mapping(source = "flightNumber", target = "flight.flightNumber")
        Reservation toEntity(ReservationDto reservationDto);

        @InheritInverseConfiguration(name = "toEntity")
        ReservationDto toDto(Reservation reservation);

        /**
         * Partially updates an existing {@link Reservation} entity from a DTO.
         * Only non-null fields from the DTO are updated.
         */
        @InheritConfiguration(name = "toEntity")
        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        Reservation partialUpdate(ReservationDto reservationDto, @MappingTarget Reservation reservation);

        List<Reservation> toEntity(List<ReservationDto> reservationDto);

        @InheritInverseConfiguration(name = "toEntity")
        List<ReservationDto> toDto(List<Reservation> reservation);


    }
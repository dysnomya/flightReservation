package com.katarzynachojniak.staz.flightreservation.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.katarzynachojniak.staz.flightreservation.seat.SeatDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Flight}
 */
public class FlightDto implements Serializable {

    @NotNull(message = "Flight Number is required")
    @Pattern(regexp = "^[A-Z]{2,3}[0-9]+$", message = "Flight Number pattern is not correct")
    private final String flightNumber;

    @NotNull(message = "Departure place is required")
    private final String from;

    @NotNull(message = "Arrival place is required")
    private final String to;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Departure Date is required")
    private final LocalDateTime departureDate;

    private final Integer durationInMinutes;

    private final Boolean roundTrip;

    private final Set<SeatDto> seats;


    // constructor, getters, equals/hashCode

    public FlightDto(String flightNumber, String from, String to, LocalDateTime departureDate, Integer durationInMinutes, Boolean roundTrip, Set<SeatDto> seats) {
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.durationInMinutes = durationInMinutes;
        this.roundTrip = roundTrip;
        this.seats = seats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public Boolean getRoundTrip() {
        return roundTrip;
    }

    public Set<SeatDto> getSeats() {
        return seats;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FlightDto flightDto)) return false;
        return Objects.equals(getFlightNumber(), flightDto.getFlightNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getFlightNumber());
    }

    @Override
    public String toString() {
        return "FlightDto{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departurePlace='" + from + '\'' +
                ", arrivalPlace='" + to + '\'' +
                ", durationMinutes=" + durationInMinutes +
                ", roundTrip=" + roundTrip +
                ", seats=" + seats +
                '}';
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }
}
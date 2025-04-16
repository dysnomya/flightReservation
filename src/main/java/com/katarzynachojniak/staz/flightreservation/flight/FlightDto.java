package com.katarzynachojniak.staz.flightreservation.flight;

import com.katarzynachojniak.staz.flightreservation.seat.SeatDto;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Flight}
 */
public class FlightDto implements Serializable {

    @NotNull(message = "Flight Number is required")
    private final String flightNumber;
    @NotNull(message = "Departure place is required")
    private final String departurePlace;
    @NotNull(message = "Arrival place is required")
    private final String arrivalPlace;
    private final int durationMinutes; // duration is 0 by default because of int usage
    private final boolean roundTrip; // round trip is false by default because of boolean usage
    private final Set<SeatDto> seats;

    public FlightDto(String flightNumber, String departurePlace, String arrivalPlace, int durationMinutes, boolean roundTrip, Set<SeatDto> seats) {
        this.flightNumber = flightNumber;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.durationMinutes = durationMinutes;
        this.roundTrip = roundTrip;
        this.seats = seats == null ? new HashSet<>() : seats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public boolean getRoundTrip() {
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
                ", departurePlace='" + departurePlace + '\'' +
                ", arrivalPlace='" + arrivalPlace + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", roundTrip=" + roundTrip +
                ", seats=" + seats +
                '}';
    }
}
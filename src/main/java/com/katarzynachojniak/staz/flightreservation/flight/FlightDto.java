package com.katarzynachojniak.staz.flightreservation.flight;

import com.katarzynachojniak.staz.flightreservation.seat.SeatDto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Flight}
 */
public class FlightDto implements Serializable {
    private final String flightNumber;
    private final String departurePlace;
    private final String arrivalPlace;
    private final int durationMinutes;
    private final boolean roundTrip;
    private final Set<SeatDto> seats;

    public FlightDto(String flightNumber, String departurePlace, String arrivalPlace, int durationMinutes, boolean roundTrip, Set<SeatDto> seats) {
        this.flightNumber = flightNumber;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.durationMinutes = durationMinutes;
        this.roundTrip = roundTrip;
        this.seats = seats;
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
}
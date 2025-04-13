package com.katarzynachojniak.staz.flightreservation.flight;

import com.katarzynachojniak.staz.flightreservation.seat.SeatDto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Flight}
 */
public class FlightDto implements Serializable {
    private final long id;
    private final String flightNumber;
    private final String departurePlace;
    private final String arrivalPlace;
    private final int durationMinutes;
    private final boolean roundTrip;
    private final Set<SeatDto> seats;

    public FlightDto(long id, String flightNumber, String departurePlace, String arrivalPlace, int durationMinutes, boolean roundTrip, Set<SeatDto> seats) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.durationMinutes = durationMinutes;
        this.roundTrip = roundTrip;
        this.seats = seats;
    }

    public long getId() {
        return id;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDto entity = (FlightDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.flightNumber, entity.flightNumber) &&
                Objects.equals(this.departurePlace, entity.departurePlace) &&
                Objects.equals(this.arrivalPlace, entity.arrivalPlace) &&
                Objects.equals(this.durationMinutes, entity.durationMinutes) &&
                Objects.equals(this.roundTrip, entity.roundTrip) &&
                Objects.equals(this.seats, entity.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, departurePlace, arrivalPlace, durationMinutes, roundTrip, seats);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "flightNumber = " + flightNumber + ", " +
                "departurePlace = " + departurePlace + ", " +
                "arrivalPlace = " + arrivalPlace + ", " +
                "durationMinutes = " + durationMinutes + ", " +
                "roundTrip = " + roundTrip + ", " +
                "seats = " + seats + ")";
    }
}
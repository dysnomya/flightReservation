package com.katarzynachojniak.staz.flightreservation.reservation;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Reservation}
 */
public class ReservationCreateDto implements Serializable {
    private final String flightNumber;
    private final String seatNumber;
    private final Long passengerId;
    private final boolean hasDeparted;

    public ReservationCreateDto(String flightNumber, String seatNumber, Long passengerId, boolean hasDeparted) {
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
        this.passengerId = passengerId;
        this.hasDeparted = hasDeparted;
    }


    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public boolean getHasDeparted() {
        return hasDeparted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationCreateDto entity = (ReservationCreateDto) o;
        return Objects.equals(this.flightNumber, entity.flightNumber) &&
                Objects.equals(this.seatNumber, entity.seatNumber) &&
                Objects.equals(this.passengerId, entity.passengerId) &&
                Objects.equals(this.hasDeparted, entity.hasDeparted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, seatNumber, passengerId, hasDeparted);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "flightId = " + flightNumber + ", " +
                "seatId = " + seatNumber + ", " +
                "passengerId = " + passengerId + ", " +
                "hasDeparted = " + hasDeparted + ")";
    }
}
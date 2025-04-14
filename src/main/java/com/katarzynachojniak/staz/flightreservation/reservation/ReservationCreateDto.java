package com.katarzynachojniak.staz.flightreservation.reservation;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Reservation}
 */
public class ReservationCreateDto implements Serializable {
    private final long flightId;
    private final String seatNumber;
    private final Long passengerId;
    private final boolean hasDeparted;

    public ReservationCreateDto(long flightId, String seatNumber, Long passengerId, boolean hasDeparted) {
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.passengerId = passengerId;
        this.hasDeparted = hasDeparted;
    }

    public long getFlightId() {
        return flightId;
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
        return Objects.equals(this.flightId, entity.flightId) &&
                Objects.equals(this.seatNumber, entity.seatNumber) &&
                Objects.equals(this.passengerId, entity.passengerId) &&
                Objects.equals(this.hasDeparted, entity.hasDeparted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, seatNumber, passengerId, hasDeparted);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "flightId = " + flightId + ", " +
                "seatId = " + seatNumber + ", " +
                "passengerId = " + passengerId + ", " +
                "hasDeparted = " + hasDeparted + ")";
    }
}
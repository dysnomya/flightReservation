package com.katarzynachojniak.staz.flightreservation.reservation;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Reservation}
 *
 * <p>Used for creating and updating reservation data.</p>
 */
public class ReservationCreateDto implements Serializable {

    /**
     * Flight number associated with the reservation. No need to enter whole flight data, just flight Number.
     */
    private final String flightNumber;

    /**
     * Seat number associated with the flight and the reservation. No need to enter whole seat data, just flight Number.
     */
    private final String seatNumber;

    /**
     * Passenger unique identifier. No need to create new passenger, id is enough.
     */

    private final Long passengerId;

    /**
     * Boolean indicating whether passenger has departed
     */
    private final Boolean hasDeparted;

    public ReservationCreateDto(String flightNumber, String seatNumber, Long passengerId, Boolean hasDeparted) {
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

    public Boolean getHasDeparted() {
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
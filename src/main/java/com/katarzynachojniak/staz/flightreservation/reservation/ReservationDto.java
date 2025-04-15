package com.katarzynachojniak.staz.flightreservation.reservation;

import com.katarzynachojniak.staz.flightreservation.passenger.PassengerDto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Reservation}
 */
public class ReservationDto implements Serializable {
    private final Long id;
    private final String flightNumber;
    private final String seatNumber;
    private final PassengerDto passenger;

    public ReservationDto(Long id, String flightNumber, String seatNumber, PassengerDto passenger) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
        this.passenger = passenger;
    }

    public Long getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public PassengerDto getPassenger() {
        return passenger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDto entity = (ReservationDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.flightNumber, entity.flightNumber) &&
                Objects.equals(this.seatNumber, entity.seatNumber) &&
                Objects.equals(this.passenger, entity.passenger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, seatNumber, passenger);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "flightFlightNumber = " + flightNumber + ", " +
                "seatSeatNumber = " + seatNumber + ", " +
                "passenger = " + passenger + ")";
    }
}
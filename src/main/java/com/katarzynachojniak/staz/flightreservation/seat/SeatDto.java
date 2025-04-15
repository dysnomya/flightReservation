package com.katarzynachojniak.staz.flightreservation.seat;

import com.katarzynachojniak.staz.flightreservation.reservation.Reservation;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.katarzynachojniak.staz.flightreservation.seat.Seat}
 */
public class SeatDto implements Serializable {
    private final String seatNumber;
    private final Boolean available;

    public SeatDto(String seatNumber, Reservation reservation) {
        this.seatNumber = seatNumber;
        this.available = reservation != null;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Boolean getAvailable() {
        return available;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SeatDto seatDto)) return false;
        return Objects.equals(getSeatNumber(), seatDto.getSeatNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getSeatNumber());
    }

    @Override
    public String toString() {
        return "SeatDto{" +
                "seatNumber='" + seatNumber + '\'' +
                '}';
    }
}
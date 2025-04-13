package com.katarzynachojniak.staz.flightreservation.seat;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.katarzynachojniak.staz.flightreservation.seat.Seat}
 */
public class SeatDto implements Serializable {
    private final String seatNumber;

    public SeatDto(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
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
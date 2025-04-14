package com.katarzynachojniak.staz.flightreservation.reservation;

import com.katarzynachojniak.staz.flightreservation.flight.FlightDto;
import com.katarzynachojniak.staz.flightreservation.passenger.PassengerDto;
import com.katarzynachojniak.staz.flightreservation.seat.SeatDto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Reservation}
 */
public class ReservationDto implements Serializable {
    private final Long id;
    private final FlightDto flight;
    private final SeatDto seat;
    private final PassengerDto passenger;
    private final Boolean hasDeparted;

    public ReservationDto(Long id, FlightDto flight, SeatDto seat, PassengerDto passenger, Boolean hasDeparted) {
        this.id = id;
        this.flight = flight;
        this.seat = seat;
        this.passenger = passenger;
        this.hasDeparted = hasDeparted;
    }

    public Long getId() {
        return id;
    }

    public FlightDto getFlight() {
        return flight;
    }

    public SeatDto getSeat() {
        return seat;
    }

    public PassengerDto getPassenger() {
        return passenger;
    }

    public Boolean getHasDeparted() {
        return hasDeparted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDto entity = (ReservationDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.flight, entity.flight) &&
                Objects.equals(this.seat, entity.seat) &&
                Objects.equals(this.passenger, entity.passenger) &&
                Objects.equals(this.hasDeparted, entity.hasDeparted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight, seat, passenger, hasDeparted);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "flight = " + flight + ", " +
                "seat = " + seat + ", " +
                "passenger = " + passenger + ", " +
                "hasDeparted = " + hasDeparted + ")";
    }
}
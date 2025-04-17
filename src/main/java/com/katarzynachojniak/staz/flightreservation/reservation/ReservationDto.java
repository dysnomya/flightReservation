package com.katarzynachojniak.staz.flightreservation.reservation;

import com.katarzynachojniak.staz.flightreservation.passenger.PassengerDto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Reservation}
 *
 * <p>Used for representing Reservation class</p>
 */
public class ReservationDto implements Serializable {

    private final Long id;

    /**
     * Flight number associated with the reservation. No need to see every detail about flight, so it's String
     */
    private final String flightNumber;

    /**
     * Seat number associated with the flight and the reservation. No need to see every detail about seat, so it's String
     */
    private final String seatNumber;

    /**
     * Passenger assigned to the reservation. Want to see evey detail of passenger - so it's whole passenger class.
     */
    private final PassengerDto passenger;

    /**
     * Boolean indicating whether passenger has departed
     */
    private final Boolean hasDeparted;


    public ReservationDto(Long id, String flightNumber, String seatNumber, PassengerDto passenger, Boolean hasDeparted) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
        this.passenger = passenger;
        this.hasDeparted = hasDeparted;
    }

    // constructor, getters, equals/hashCode

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

    public Boolean getHasDeparted() {
        return hasDeparted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDto entity = (ReservationDto) o;
        return Objects.equals(this.flightNumber, entity.flightNumber) &&
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
package com.katarzynachojniak.staz.flightreservation.seat;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import jakarta.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue
    private long id;

    private String seatNumber;

    private Boolean reserved;

    @ManyToOne
    @JoinColumn
    private Flight flight;

    protected Seat() {}

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.reserved = false;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

}

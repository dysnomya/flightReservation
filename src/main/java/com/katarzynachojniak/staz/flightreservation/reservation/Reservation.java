package com.katarzynachojniak.staz.flightreservation.reservation;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.passenger.Passenger;
import com.katarzynachojniak.staz.flightreservation.seat.Seat;
import jakarta.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    private boolean hasDeparted;

    public Reservation() {}

    public Reservation(Flight flight, Seat seat, Passenger passenger, Boolean hasDeparted) {
        this.flight = flight;
        this.seat = seat;
        this.passenger = passenger;
        this.hasDeparted = hasDeparted;
    }


    public Long getId() {
        return id;
    }

    public Flight getFlight() {
        return flight;
    }

    public Seat getSeat() {
        return seat;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Boolean getHasDeparted() {
        return hasDeparted;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setHasDeparted(Boolean hasDeparted) {
        this.hasDeparted = hasDeparted;
    }
}
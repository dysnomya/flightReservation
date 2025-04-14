package com.katarzynachojniak.staz.flightreservation.seat;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.reservation.Reservation;
import jakarta.persistence.*;

@Entity
@Table(name = "Seat", uniqueConstraints = {
        @UniqueConstraint(name = "uc_seat_seatnumber_flight_id", columnNames = {"seatNumber", "flight_id"})
})
public class Seat {

    @Id
    @GeneratedValue
    private long id;

    private String seatNumber;

    @OneToOne(mappedBy = "seat")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;


    public Seat() {}

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public long getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Reservation getReservation() {
        return reservation;
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

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}

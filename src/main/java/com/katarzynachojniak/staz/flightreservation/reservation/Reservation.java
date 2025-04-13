package com.katarzynachojniak.staz.flightreservation.reservation;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import jakarta.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private long reservationNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flightNumber")
    private Flight flight;


}

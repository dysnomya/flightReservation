package com.katarzynachojniak.staz.flightreservation.reservation;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.passenger.Passenger;
import com.katarzynachojniak.staz.flightreservation.seat.Seat;
import jakarta.persistence.*;


/**
 * Entity representing a reservation in the flight reservation system.
 *
 * <p>Each reservation connects a {@link Passenger} to a specific {@link Seat} on a {@link Flight}.
 * Reservations track whether the passenger has departed.</p>
 *
 * <p><b>Relationships:</b></p>
 * <ul>
 *     <li>{@code @ManyToOne} to {@link Flight} - multiple reservations can belong to the same flight.</li>
 *     <li>{@code @OneToOne} to {@link Seat} - a seat can be reserved by only one reservation.</li>
 *     <li>{@code @ManyToOne} to {@link Passenger} - a passenger can have multiple reservations.</li>
 * </ul>
 */
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id; // Primary key for the reservation

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight; // Flight associated with this reservation

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat; // Unique seat reserved on the flight

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger; // Passenger who made the reservation

    private boolean hasDeparted;

    public Reservation() {
        // required by JPA
    }

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
package com.katarzynachojniak.staz.flightreservation.seat;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.reservation.Reservation;
import jakarta.persistence.*;

/**
 * Entity representing a seat on a flight in the flight reservation system.
 *
 * <p>Each seat has a seat number and is associated with a specific {@link Flight}.
 * A seat can be assigned to a single {@link Reservation}.</p>
 *
 * <p><b>Relationships:</b></p>
 * <ul>
 *     <li>{@code @ManyToOne} to {@link Flight} – multiple seats belong to one flight.</li>
 *     <li>{@code @OneToOne} mapped by {@code seat} in {@link Reservation} – each seat may be reserved by one reservation.</li>
 * </ul>
 *
 * <p>A unique constraint ensures that a seat number is unique within a flight.</p>
 */
@Entity
@Table(name = "Seat", uniqueConstraints = {
        @UniqueConstraint(name = "uc_seat_seatnumber_flight_id", columnNames = {"seatNumber", "flight_id"})
})
public class Seat {

    @Id
    @GeneratedValue
    private long id; // Primary key for the seat

    private String seatNumber; // Identifier for the seat (e.g., "12A")

    @OneToOne(mappedBy = "seat")
    private Reservation reservation; // The reservation that holds this seat

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight; // The flight to which this seat belongs


    public Seat() {
        // required by JPA
    }

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

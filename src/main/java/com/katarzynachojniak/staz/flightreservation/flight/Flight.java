package com.katarzynachojniak.staz.flightreservation.flight;

import com.katarzynachojniak.staz.flightreservation.reservation.Reservation;
import com.katarzynachojniak.staz.flightreservation.seat.Seat;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    private String flightNumber;
    private String departurePlace;
    private String arrivalPlace;
    private int durationMinutes;
    private boolean roundTrip;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Seat> seats = new HashSet<>();

    @OneToMany(mappedBy = "flight", orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();


    protected Flight() {}

    public Flight(String flightNumber, String departurePlace, String arrivalPlace, int durationMinutes, Boolean roundTrip, Set<Seat> seats) {
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.durationMinutes = durationMinutes;
        this.flightNumber = flightNumber;
        this.roundTrip = roundTrip;
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public boolean isRoundTrip() {
        return roundTrip;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setRoundTrip(boolean roundTrip) {
        this.roundTrip = roundTrip;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public void removeSeat(Seat seat) {
        seats.remove(seat);
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }
}

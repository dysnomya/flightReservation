package com.katarzynachojniak.staz.flightreservation.flight;

import com.katarzynachojniak.staz.flightreservation.reservation.Reservation;
import com.katarzynachojniak.staz.flightreservation.seat.Seat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


/**
 * Entity representing a flight in the flight reservation system.
 *
 * <p>Includes details like flight number, departure and arrival locations, departure date, flight duration,
 * and whether it is a round trip. It also contains a set of associated
 * {@link Seat} entities.</p>
 *
 * <p>When a flight is deleted, all associated {@link Seat} entities are also removed due to cascading.</p>
 */
@Entity
public class Flight {

    @NotNull
    @Id
    @Column(nullable = false)
    private String flightNumber;

    @Column(nullable = false)
    private String departureLocation;

    @Column(nullable = false)
    private String arrivalLocation;

    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Column
    private Integer durationInMinutes;

    @Column(nullable = false)
    private Boolean roundTrip;

    /**
     * The set of seats associated with this flight. Maintains a one-to-many relationship
     * with the {@code Seat} entity. When flight is removed, all seats are also removed due to cascading.
     */
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Seat> seats = new HashSet<>();

    public Flight() {
        // required by JPA
    }


    public Flight(String flightNumber, String departureLocation, String arrivalLocation, LocalDateTime departureDate, Integer durationInMinutes, Boolean roundTrip, Set<Seat> seats) {
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.durationInMinutes = durationInMinutes;
        this.flightNumber = flightNumber;
        this.roundTrip = roundTrip;
        this.seats = seats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public Boolean isRoundTrip() {
        return roundTrip;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureLocation(String departurePlace) {
        this.departureLocation = departurePlace;
    }

    public void setArrivalLocation(String arrivalPlace) {
        this.arrivalLocation = arrivalPlace;
    }

    public void setDurationInMinutes(int durationMinutes) {
        this.durationInMinutes = durationMinutes;
    }

    public void setRoundTrip(boolean roundTrip) {
        this.roundTrip = roundTrip;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public void removeSeat(Seat seat) {
        seats.remove(seat);
    }
}

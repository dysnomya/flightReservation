package com.katarzynachojniak.staz.flightreservation.passenger;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

/**
 * Entity representing a passenger in the flight reservation system.
 *
 * <p>It contains basic personal information such as name, surname, email and phone number of a passenger.
 *
 * <p>The passenger is uniquely identified by an auto-generated ID.</p>
 */
@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column
    private String phoneNumber;


    public Passenger() {
        // required by JPA
    }

    public Passenger(String name, String surname, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
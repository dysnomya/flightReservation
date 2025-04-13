package com.katarzynachojniak.staz.flightreservation.passenger;

import jakarta.validation.constraints.Email;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Passenger}
 */
public class PassengerDto implements Serializable {
    private final Long id;
    private final String name;
    private final String surname;
    @Email
    private final String email;
    private final String phoneNumber;

    public PassengerDto(Long id, String name, String surname, String email, String phoneNumber) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerDto entity = (PassengerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.surname, entity.surname) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.phoneNumber, entity.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, phoneNumber);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "surname = " + surname + ", " +
                "email = " + email + ", " +
                "phoneNumber = " + phoneNumber + ")";
    }
}
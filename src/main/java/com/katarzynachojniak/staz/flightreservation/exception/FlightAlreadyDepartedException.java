package com.katarzynachojniak.staz.flightreservation.exception;

public class FlightAlreadyDepartedException extends RuntimeException {
    public FlightAlreadyDepartedException(String message) {
        super(message);
    }
}

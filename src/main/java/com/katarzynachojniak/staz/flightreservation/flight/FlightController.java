package com.katarzynachojniak.staz.flightreservation.flight;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * REST controller for handling flight-related operations.
 *
 * <p>Provides endpoints for managing flights, including:</p>
 * <ul>
 *     <li>Retrieving all flights</li>
 *     <li>Fetching a specific flight by ID</li>
 *     <li>Creating a new flight</li>
 *     <li>Updating an existing flight</li>
 *     <li>Deleting a flight</li>
 * </ul>
 *
 * <p>Includes basic error handling for validation and database constraint issues.</p>
 *
 * <p>Base URL: <code>/flights</code></p>
 */
@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Returns a list of all available flights.
     */
    @GetMapping
    public List<FlightDto> getAllFlights() {
        return flightService.getAllFlights();
    }

    /**
     * Creates a new flight entry.
     *
     * @param flightDto flight data sent in request body
     * @return created flight with 201 (Created) status
     */
    @PostMapping
    public ResponseEntity<FlightDto> createFlight(@Valid @RequestBody FlightDto flightDto) {
        return new ResponseEntity<>(flightService.createFlight(flightDto), HttpStatus.CREATED);
    }

    /**
     * Returns flight details by its ID (flight number).
     *
     * @param id flight number
     * @return the flight if found, or 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable String id) {
        FlightDto flight = flightService.getFlightDtoByFlightNumber(id);

        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates an existing flight.
     *
     * @param id flight number
     * @param flightDto updated flight data
     * @return updated flight if found, or 404 (Not Found)
     */
    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable String id, @RequestBody FlightDto flightDto) {
        FlightDto changedFlight = flightService.updateFlight(id, flightDto);

        if (changedFlight != null) {
            return ResponseEntity.ok(changedFlight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a flight by its ID.
     *
     * @param id flight number
     * @return 204 (No Content) whether or not the flight existed
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }





    // ========== Exception Handlers ==========

    /**
     * Handles SQL constraint violations (e.g. duplicate entries or invalid references).
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String handleConstraintViolationException() {
        return "Constraint violation: Duplicate or invalid reference.";
    }

    /**
     * Handles validation errors for input fields.
     *
     * @param ex exception containing validation details
     * @return map of required field names that didn't contain value
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>  handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}

package com.katarzynachojniak.staz.flightreservation.flight;

import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightDto> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping
    public ResponseEntity<FlightDto> createFlight(@Valid @RequestBody FlightDto flightDto) {
        return new ResponseEntity<>(flightService.createFlight(flightDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable String id) {
        FlightDto flight = flightService.getFlightDtoByFlightNumber(id);

        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable String id, @RequestBody FlightDto flightDto) {
        FlightDto changedFlight = flightService.updateFlight(id, flightDto);

        if (changedFlight != null) {
            return ResponseEntity.ok(changedFlight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }





    // Exception handling

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String handleConstraintViolationException() {
        return "Constraint violation: Duplicate or invalid reference.";
    }

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

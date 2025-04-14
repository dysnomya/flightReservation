package com.katarzynachojniak.staz.flightreservation.flight;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
        return new ResponseEntity<>(flightService.createFlight(flightDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        FlightDto flight = flightService.getFlightDtoById(id);

        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Long id, @RequestBody FlightDto flightDto) {
        FlightDto changedFlight = flightService.updateFlight(id, flightDto);

        if (changedFlight != null) {
            return ResponseEntity.ok(changedFlight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

}

package com.katarzynachojniak.staz.flightreservation.passenger;

import com.katarzynachojniak.staz.flightreservation.flight.FlightDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<PassengerDto> getAllPassengers() {
        return passengerService.getAllPassengers();
    }


    @PostMapping
    public ResponseEntity<PassengerDto> createPassenger(@RequestBody PassengerDto passengerDto) {
        return new ResponseEntity<>(passengerService.createPassenger(passengerDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable Long id) {
        PassengerDto passengerDto = passengerService.getPassengerById(id);

        if (passengerDto != null) {
            return ResponseEntity.ok(passengerDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PassengerDto> updatePassenger(@PathVariable Long id, @RequestBody PassengerDto dto) {

        PassengerDto passengerDto = passengerService.updatePassenger(id, dto);

        if (passengerDto != null) {
            return ResponseEntity.ok(passengerDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }
}

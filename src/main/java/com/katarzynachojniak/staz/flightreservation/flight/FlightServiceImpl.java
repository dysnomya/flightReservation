package com.katarzynachojniak.staz.flightreservation.flight;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Implementation of the {@link FlightService} interface.
 *
 * <p>Provides the actual business logic for managing flights, interacting with the database, and performing CRUD operations.</p>
 *
 * <p>It interacts with the {@link FlightRepository} to handle flight persistence and uses {@link FlightMapper}
 * to map between {@link Flight} and {@link FlightDto} objects.</p>
 */
@Transactional
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    /**
     * Constructs a new {@link FlightServiceImpl} with the provided repository and mapper.
     *
     * @param flightRepository the repository for performing flight database operations
     * @param flightMapper the mapper used to convert between {@link Flight} and {@link FlightDto}
     */
    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    /**
     * Creates a new flight based on the provided flight data.
     *
     * <p>This method maps the provided {@link FlightDto} to a {@link Flight} entity, saves it to the database,
     * and returns the saved flight as a {@link FlightDto}.</p>
     *
     * @param flightDto the details of the flight to create
     * @return the created flight as a {@link FlightDto}
     */
    @Override
    public FlightDto createFlight(FlightDto flightDto) {
        Flight flight = flightMapper.toEntity(flightDto);
        Flight savedFlight = flightRepository.save(flight);

        return flightMapper.toDto(savedFlight);
    }

    /**
     * Updates an existing flight with the new data provided in the {@link FlightDto}.
     *
     * <p>This method retrieves the existing flight by its flight number, applies partial updates using the data from
     * the provided {@link FlightDto}, saves the updated flight to the database,
     * and returns the updated flight as a {@link FlightDto}.</p>
     *
     * @param flightNumber the unique identifier for the flight to be updated
     * @param dto the new data for the flight
     * @return the updated flight as a {@link FlightDto}
     */
    @Override
    public FlightDto updateFlight(String flightNumber, FlightDto dto) {

        Flight existingFlight = getFlightByFlightNumber(flightNumber);

        if (existingFlight == null) {
            return null;
        }

        flightMapper.partialUpdate(dto, existingFlight);

        Flight savedFlight = flightRepository.save(existingFlight);
        return flightMapper.toDto(savedFlight);
    }

    /**
     * Deletes a flight by its flight number.
     *
     * @param flightNumber the unique identifier for the flight to delete
     */
    @Override
    public void deleteFlight(String flightNumber) {
        flightRepository.deleteByFlightNumber(flightNumber);
    }

    /**
     * Retrieves all flights from the database.
     *
     * <p>This method retrieves a list of all flights and maps them to a list of {@link FlightDto} objects.</p>
     *
     * @return a list of all flights as {@link FlightDto} objects
     */
    @Override
    public List<FlightDto> getAllFlights() {
        List<Flight> flights = (List<Flight>) flightRepository.findAll();
        return flightMapper.toDto(flights);
    }

    /**
     * Retrieves a flight by its flight number and returns it as a {@link FlightDto}.
     *
     * @param flightNumber the unique identifier for the flight
     * @return the corresponding flight as a {@link FlightDto}, or {@code null} if no flight is found
     */
    @Override
    public FlightDto getFlightDtoByFlightNumber(String flightNumber) {
        Flight flight = getFlightByFlightNumber(flightNumber);

        return flightMapper.toDto(flight);
    }

    /**
     * Retrieves a flight entity by its flight number.
     *
     * @param flightNumber the unique identifier for the flight
     * @return the corresponding {@link Flight} entity, or {@code null} if no flight is found
     */
    @Override
    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightRepository.findById(flightNumber).orElse(null);
    }
}

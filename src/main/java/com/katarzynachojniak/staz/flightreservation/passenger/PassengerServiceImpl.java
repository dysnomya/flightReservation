package com.katarzynachojniak.staz.flightreservation.passenger;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link PassengerService} interface.
 *
 * <p>Provides the actual business logic for handling passenger related operations, interacting with the database, and performing CRUD operations.</p>
 *
 *
 * <p>It interacts with the {@link PassengerRepository} to handle passenger persistence and uses {@link PassengerMapper}
 * to map between {@link Passenger} and {@link PassengerDto} objects.</p>
 */

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    /**
     * Constructs a new {@code PassengerServiceImpl} with the required dependencies.
     *
     * @param passengerRepository the repository used to interact with the passenger database
     * @param passengerMapper the mapper used to convert between Passenger entities and DTOs
     */
    public PassengerServiceImpl(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    /**
     * Creates a new passenger and saves it to the database.
     *
     * <p>This method maps the provided {@link PassengerDto} to a {@link Passenger} entity, saves it to the database,
     * and returns the saved passenger as a {@link PassengerDto}.</p>
     *
     * @param passengerDto the data of the passenger to be created
     * @return the created passenger as a DTO
     */
    @Override
    public PassengerDto createPassenger(PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.toEntity(passengerDto);
        Passenger savedPassenger = passengerRepository.save(passenger);

        return passengerMapper.toDto(savedPassenger);
    }

    /**
     * Retrieves a passenger DTO by its ID.
     *
     * @param id the ID of the passenger
     * @return the corresponding passenger as a {@link PassengerDto} or {@code null} if no passenger is found
     */
    @Override
    public PassengerDto getPassengerDtoById(Long id) {
        Passenger passenger = getPassengerById(id);

        return passengerMapper.toDto(passenger);
    }

    /**
     * Retrieves a passenger entity by its ID.
     *
     * @param id the ID of the passenger
     * @return the corresponding {@link Passenger} or {@code null} if no passenger is found
     */
    @Override
    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves a list of all passengers from the database
     *
     * <p>This method retrieves a list of all passengers and maps them to a list of {@link PassengerDto} objects.</p>.
     *
     * @return a list of all passengers as {@link PassengerDto}
     */
    @Override
    public List<PassengerDto> getAllPassengers() {
        List<Passenger> passengers = (List<Passenger>) passengerRepository.findAll();

        return passengerMapper.toDto(passengers);
    }

    /**
     * Updates the data of an existing passenger.
     *
     * <p>This method retrieves the existing passenger by ID, applies partial updates using the data from the
     * provided PassengerDto, saves the updated passenger to the database, and returns the updated passenger data
     * as PassengerDto.</p>
     *
     * @param id the ID of the passenger to be updated
     * @param passengerDto the updated data
     * @return the updated passenger as a {@link PassengerDto}, or {@code null} if the passenger does not exist
     */
    @Override
    public PassengerDto updatePassenger(Long id, PassengerDto passengerDto) {
        Passenger existingPassenger = getPassengerById(id);

        if (existingPassenger == null) {
            return null;
        }

        passengerMapper.partialUpdate(passengerDto, existingPassenger);

        Passenger savedPassenger = passengerRepository.save(existingPassenger);
        return passengerMapper.toDto(savedPassenger);
    }


    /**
     * Deletes a passenger by their ID.
     *
     * @param id the ID of the passenger to delete
     */
    @Override
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}

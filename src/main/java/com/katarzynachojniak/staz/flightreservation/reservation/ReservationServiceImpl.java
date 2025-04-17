package com.katarzynachojniak.staz.flightreservation.reservation;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.flight.FlightMapper;
import com.katarzynachojniak.staz.flightreservation.flight.FlightService;
import com.katarzynachojniak.staz.flightreservation.mail.MailService;
import com.katarzynachojniak.staz.flightreservation.passenger.Passenger;
import com.katarzynachojniak.staz.flightreservation.passenger.PassengerMapper;
import com.katarzynachojniak.staz.flightreservation.passenger.PassengerService;
import com.katarzynachojniak.staz.flightreservation.seat.Seat;
import com.katarzynachojniak.staz.flightreservation.seat.SeatMapper;
import com.katarzynachojniak.staz.flightreservation.seat.SeatService;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Implementation of {@link ReservationService} for managing flight reservations.
 *
 * <p>This service handles creation, retrieval, updating, and deletion of reservations.
 * It integrates with services for passengers, flights, and seats to validate and link entities.</p>
 *
 * <p>It interacts with {@link ReservationRepository} to handle reservation persistence and uses {@link ReservationMapper}
 * to map between {@link Reservation} and {@link ReservationDto} objects.</p>
 */
@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final PassengerService passengerService;
    private final FlightService flightService;
    private final SeatService seatService;
    private final MailService mailService;

    /**
     * Constructs a new {@code ReservationServiceImpl} with the required dependencies.
     *
     * @param reservationRepository the repository for accessing reservation data
     * @param reservationMapper the mapper for converting between Reservation and ReservationDto
     * @param passengerService the service for handling passenger-related operations
     * @param flightService the service for handling flight-related operations
     * @param seatService the service for handling seat-related operations
     * @param mailService the service used for sending emails
     */
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper,
                                  PassengerService passengerService, FlightService flightService,
                                  SeatService seatService, MailService mailService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.passengerService = passengerService;
        this.flightService = flightService;
        this.seatService = seatService;
        this.mailService = mailService;
    }

    /**
     * Creates a new reservation and sends a confirmation email.
     *
     * <p>Retrieves related entities using their IDs, creates a {@link Reservation}, saves it,
     * and sends an email via {@link MailService}.</p>
     *
     * @param dto the data needed to create a reservation
     * @return the created reservation as a {@link ReservationDto}
     */
    @Override
    public ReservationDto createReservation(ReservationCreateDto dto) {
        Passenger passenger = passengerService.getPassengerById(dto.getPassengerId());
        Flight flight = flightService.getFlightByFlightNumber(dto.getFlightNumber());
        Seat seat = seatService.getSeatBySeatNumberAndFlight(dto.getSeatNumber(), flight);

        Reservation reservation = new Reservation(flight, seat, passenger, dto.getHasDeparted());
        Reservation savedReservation = reservationRepository.save(reservation);

        mailService.sendReservationCreateMail(savedReservation);

        return reservationMapper.toDto(savedReservation);
    }

    /**
     * Retrieves a reservation as a DTO by its ID.
     *
     * @param id the ID of the reservation
     * @return the corresponding {@link ReservationDto}, or {@code null} if not found
     */
    @Override
    public ReservationDto getReservationDtoById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        return reservationMapper.toDto(reservation);
    }

    /**
     * Retrieves a reservation entity by its ID.
     *
     * @param id the ID of the reservation
     * @return the corresponding {@link Reservation} entity, or {@code null} if not found
     */
    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }


    /**
     * Retrieves all reservations.
     *
     * @return a list of all reservations as {@link ReservationDto}
     */
    @Override
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();

        return reservationMapper.toDto(reservations);
    }


    /**
     * Updates an existing reservation with new values from the DTO.
     *
     * <p>Looks up the current reservation by ID and updates fields based on the provided data.
     * If a new flight or seat is specified, the method verifies and updates the reservation accordingly.</p>
     *
     * @param id the ID of the reservation to update
     * @param dto the new data for the reservation
     * @return the updated {@link ReservationDto}, or {@code null} if the reservation is not found
     */
    @Override
    public ReservationDto updateReservation(Long id, ReservationCreateDto dto) { // TODO
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);

        if (existingReservation == null) {
            return null;
        }

        Passenger passenger = dto.getPassengerId() != null
                ? passengerService.getPassengerById(dto.getPassengerId())
                : existingReservation.getPassenger();

        Flight flight = dto.getFlightNumber() != null
                ? flightService.getFlightByFlightNumber(dto.getFlightNumber())
                : existingReservation.getFlight();

        Seat seat = dto.getSeatNumber() != null
                ? seatService.getSeatBySeatNumberAndFlight(dto.getSeatNumber(), flight)
                : existingReservation.getSeat();

        // TODO? seat cant be already reserved

        existingReservation.setPassenger(passenger);
        existingReservation.setFlight(flight);
        existingReservation.setSeat(seat);

        Reservation savedReservation = reservationRepository.save(existingReservation);
        return reservationMapper.toDto(savedReservation);
    }

    /**
     * Deletes a reservation by its ID and clears the associated seat (so that it doesn't hold any reference to reservation).
     *
     * @param id the ID of the reservation to delete
     */
    @Override
    public void deleteReservation(Long id) {

        Seat seat = getReservationById(id).getSeat();

        if (seat != null) {
            seat.setReservation(null);
            seatService.save(seat);
        }

        reservationRepository.deleteById(id);
    }
}

package com.katarzynachojniak.staz.flightreservation.reservation;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.flight.FlightDto;
import com.katarzynachojniak.staz.flightreservation.flight.FlightService;
import com.katarzynachojniak.staz.flightreservation.passenger.Passenger;
import com.katarzynachojniak.staz.flightreservation.passenger.PassengerService;
import com.katarzynachojniak.staz.flightreservation.seat.Seat;
import com.katarzynachojniak.staz.flightreservation.seat.SeatDto;
import com.katarzynachojniak.staz.flightreservation.seat.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final PassengerService passengerService;
    private final FlightService flightService;
    private final SeatService seatService;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationMapper reservationMapper,
                                  PassengerService passengerService,
                                  FlightService flightService,
                                  SeatService seatService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.passengerService = passengerService;
        this.flightService = flightService;
        this.seatService = seatService;
    }

    @Override
    public ReservationDto createReservation(ReservationCreateDto dto) {
        Passenger passenger = passengerService.getPassengerById(dto.getPassengerId());
        Flight flight = flightService.getFlightById(dto.getFlightId());
        Seat seat = seatService.getSeatBySeatNumberAndFlight(dto.getSeatNumber(), flight);

        Reservation reservation = new Reservation(flight, seat, passenger, dto.getHasDeparted());
        Reservation savedReservation = reservationRepository.save(reservation);

        return reservationMapper.toDto(savedReservation);
    }

    @Override
    public ReservationDto getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        return reservationMapper.toDto(reservation);
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();

        return reservationMapper.toDto(reservations);
    }

    @Override
    public ReservationDto updateReservation(Long id, ReservationDto reservationDto) { // TODO
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);

        if (existingReservation == null) {
            return null;
        }

        reservationMapper.partialUpdate(reservationDto, existingReservation);

        Reservation savedReservation = reservationRepository.save(existingReservation);
        return reservationMapper.toDto(savedReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}

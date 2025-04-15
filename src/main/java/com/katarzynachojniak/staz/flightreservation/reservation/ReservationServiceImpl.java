package com.katarzynachojniak.staz.flightreservation.reservation;

import com.katarzynachojniak.staz.flightreservation.flight.Flight;
import com.katarzynachojniak.staz.flightreservation.flight.FlightMapper;
import com.katarzynachojniak.staz.flightreservation.flight.FlightService;
import com.katarzynachojniak.staz.flightreservation.passenger.Passenger;
import com.katarzynachojniak.staz.flightreservation.passenger.PassengerMapper;
import com.katarzynachojniak.staz.flightreservation.passenger.PassengerService;
import com.katarzynachojniak.staz.flightreservation.seat.Seat;
import com.katarzynachojniak.staz.flightreservation.seat.SeatMapper;
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
    private final FlightMapper flightMapper;
    private final SeatMapper seatMapper;
    private final PassengerMapper passengerMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper,
                                  PassengerService passengerService, FlightService flightService, SeatService seatService,
                                  FlightMapper flightMapper, SeatMapper seatMapper, PassengerMapper passengerMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.passengerService = passengerService;
        this.flightService = flightService;
        this.seatService = seatService;
        this.flightMapper = flightMapper;
        this.seatMapper = seatMapper;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public ReservationDto createReservation(ReservationCreateDto dto) {
        Passenger passenger = passengerService.getPassengerById(dto.getPassengerId());
        Flight flight = flightService.getFlightByFlightNumber(dto.getFlightNumber());
        Seat seat = seatService.getSeatBySeatNumberAndFlight(dto.getSeatNumber(), flight);

        Reservation reservation = new Reservation(flight, seat, passenger, dto.getHasDeparted());
        Reservation savedReservation = reservationRepository.save(reservation);

        return reservationMapper.toDto(savedReservation);
    }

    @Override
    public ReservationDto getReservationDtoById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        return reservationMapper.toDto(reservation);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();

        return reservationMapper.toDto(reservations);
    }


    // Updating with no mapper
    @Override
    public ReservationDto updateReservation(Long id, ReservationCreateDto dto) { // TODO
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);

        if (existingReservation == null) {
            return null;
        }

        Passenger passenger = dto.getPassengerId() != null
                ? passengerService.getPassengerById(dto.getPassengerId())
                : existingReservation.getPassenger();

        System.out.println(dto.getFlightNumber());

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

//        ReservationDto reservationDto = new ReservationDto(null, flightMapper.toDto(flight),
//                seatMapper.toDto(seat), passengerMapper.toDto(passenger), dto.getHasDeparted());
//
//        reservationMapper.partialUpdate(reservationDto, existingReservation);

        Reservation savedReservation = reservationRepository.save(existingReservation);
        return reservationMapper.toDto(savedReservation);
    }

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

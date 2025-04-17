package com.katarzynachojniak.staz.flightreservation.flight;

import com.katarzynachojniak.staz.flightreservation.seat.Seat;
import com.katarzynachojniak.staz.flightreservation.seat.SeatDto;
import com.katarzynachojniak.staz.flightreservation.seat.SeatMapper;
import com.katarzynachojniak.staz.flightreservation.seat.SeatService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.OptimisticLockingFailureException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FlightServiceImplTest {
    private FlightRepository flightRepository;
    private FlightMapper flightMapper;
    private FlightServiceImpl flightService;

    private FlightDto flightDto;
    private Flight flight;
    private Flight savedFlight;
    private Set<SeatDto> seatsDto = new HashSet<>();
    private Set<Seat> seats = new HashSet<>();

    @BeforeEach
    void setUp() {
        flightRepository = mock(FlightRepository.class);
        flightMapper = mock(FlightMapper.class);
        SeatMapper seatMapper = mock(SeatMapper.class);
        SeatService seatService = mock(SeatService.class);
        flightService = new FlightServiceImpl(flightRepository, flightMapper, seatMapper, seatService);

        // create example objects
        seatsDto.add(new SeatDto("A1", null));
        seatsDto.add(new SeatDto("A2", null));

        seats.add(new Seat("A1"));
        seats.add(new Seat("A2"));

        flightDto = new FlightDto(
                "NY1001",
                "New York",
                "London",
                LocalDateTime.of(2025, Month.JUNE, 16, 14, 38),
                420,
                false,
                seatsDto
        );

        flight = new Flight(
                "NY1001",
                "New York",
                "London",
                LocalDateTime.of(2025, Month.JUNE, 16, 14, 38),
                420,
                false,
                seats
        );

        savedFlight = flight;

    }

    @Test
    void testCreateFlight() {
        when(flightMapper.toEntity(flightDto)).thenReturn(flight);
        when(flightRepository.save(flight)).thenReturn(savedFlight);
        when(flightMapper.toDto(savedFlight)).thenReturn(flightDto);

        FlightDto result = flightService.createFlight(flightDto);

        assertEquals(flightDto, result);
        verify(flightMapper, times(1)).toEntity(flightDto);
        verify(flightRepository, times(1)).save(flight);
        verify(flightMapper, times(1)).toDto(savedFlight);
    }

    @Test
    void testCreateFlight_FlightAlreadyExists() {
        when(flightMapper.toEntity(flightDto)).thenReturn(flight);
        when(flightRepository.save(flight)).thenThrow(OptimisticLockingFailureException.class);

        assertThrows(OptimisticLockingFailureException.class, () -> flightService.createFlight(flightDto));
        verify(flightMapper, times(1)).toEntity(flightDto);
        verify(flightRepository, times(1)).save(flight);
        verify(flightMapper, never()).toDto(flight);
    }

    @Test
    void testCreateFlight_NullFlight() {
        assertThrows(IllegalArgumentException.class, () -> flightService.createFlight(null));
        verify(flightMapper, never()).toEntity((FlightDto) any());
        verify(flightRepository, never()).save(any());
        verify(flightMapper, never()).toDto(flight);
    }

    @Test
    void testCreateFlight_WrongFlightNumberFormat() {
        when(flightMapper.toEntity(flightDto)).thenThrow(ConstraintViolationException.class);


        assertThrows(ConstraintViolationException.class, () -> flightService.createFlight(flightDto));
        verify(flightMapper, times(1)).toEntity(flightDto);
        verify(flightRepository, never()).save(any());
        verify(flightMapper, never()).toDto(flight);
    }
}
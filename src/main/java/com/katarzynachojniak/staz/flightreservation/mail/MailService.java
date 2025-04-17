package com.katarzynachojniak.staz.flightreservation.mail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.katarzynachojniak.staz.flightreservation.reservation.Reservation;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service responsible for sending email notifications related to reservations.
 *
 * <p>This service uses {@link JavaMailSender} to send templated email messages to passengers,
 * informing them about successful reservation creation.</p>
 */
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final SimpleMailMessage templateMessage;

    /**
     * Constructs a new {@code MailService} with the required dependencies.
     *
     * @param mailSender the configured JavaMailSender used to send emails
     * @param templateMessage the default message template used for email content
     */
    public MailService(JavaMailSender mailSender, SimpleMailMessage templateMessage) {
        this.mailSender = mailSender;
        this.templateMessage = templateMessage;
    }

    /**
     * Sends an email notification to the passenger after a reservation is created.
     *
     * <p>The message includes reservation ID, flight number, seat number, and departure date.</p>
     *
     * @param reservation the reservation for which the email will be sent
     */
    public void sendReservationCreateMail(Reservation reservation) {

        SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
        msg.setTo(reservation.getPassenger().getEmail());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = reservation.getFlight().getDepartureDate().format(formatter);

        String mailText = String.format("""
                Hello, %s!
                
                Your reservation %d has been added to the system.
                
                Flight details:
                - Flight number: %s
                - Seat number: %s
                - Data wylotu: %s
                """,
                reservation.getPassenger().getName(),
                reservation.getId(),
                reservation.getFlight().getFlightNumber(),
                reservation.getSeat().getSeatNumber(),
                formattedDate
        );

        msg.setText(mailText);

        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

}

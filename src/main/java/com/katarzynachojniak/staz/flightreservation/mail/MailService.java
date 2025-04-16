package com.katarzynachojniak.staz.flightreservation.mail;

import com.katarzynachojniak.staz.flightreservation.reservation.Reservation;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final SimpleMailMessage templateMessage;


    public MailService(JavaMailSender mailSender, SimpleMailMessage templateMessage) {
        this.mailSender = mailSender;
        this.templateMessage = templateMessage;
    }

    public void sendReservationCreateMail(Reservation reservation) {

        SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
        msg.setTo(reservation.getPassenger().getEmail());

        String mailText = String.format("""
                Hello, %s!
                
                Your reservation %d has been added to the system.
                
                Flight details:
                - Flight number: %s
                - Seat number: %s
                - Data wylotu: 10.01.2022
                """,
                reservation.getPassenger().getName(),
                reservation.getId(),
                reservation.getFlight().getFlightNumber(),
                reservation.getSeat().getSeatNumber()
        );

        msg.setText(mailText);

        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

}

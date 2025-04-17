package com.katarzynachojniak.staz.flightreservation.mail;

import com.katarzynachojniak.staz.flightreservation.reservation.ReservationService;
import com.katarzynachojniak.staz.flightreservation.reservation.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Configuration class for setting up email.
 *
 * <p>This class configures the {@link JavaMailSender} for sending emails using Gmail's SMTP server
 * and defines a default {@link SimpleMailMessage} template for outgoing messages.</p>
 */
@Configuration
public class MailConfig {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    /**
     * Configures the {@link JavaMailSender} bean with SMTP settings for Gmail.
     *
     * @return a configured {@link JavaMailSender} instance
     */
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    /**
     * Defines a default email template used for sending flight-related messages.
     *
     * @return a {@link SimpleMailMessage} with preset "from" and "subject" fields
     */
    @Bean
    SimpleMailMessage templateMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fligh@reservation.example");
        message.setSubject("New Flight Registered!");
        return message;
    }
}

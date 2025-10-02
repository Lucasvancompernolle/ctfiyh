package com.ctfiyh.restaurant.restaurant.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;
  
 
public class ReservationMessageValidator {

    private ReservationMessage reservationMessage;
    private String message;

    public ReservationMessageValidator(ReservationMessage reservationMessage) {
         this.reservationMessage = reservationMessage;
    }
 
    public Optional<String> validationError() {
        if (reservationMessage.reservationTime() == null) {
            return Optional.of("Reservation time cannot be null");
        }

        if (reservationMessage.customerName() == null || reservationMessage.customerName().isBlank()) {
            return Optional.of("Customer name cannot be null or blank");
        }

        if (reservationMessage.numberOfGuests() <= 0) {
            return Optional.of("Number of guests must be greater than zero");
        }

        try {
            LocalDateTime.parse(reservationMessage.reservationTime());
        } catch (DateTimeParseException e) {
            return Optional.of("Invalid date/time format");
        }

        return Optional.empty(); // valid!
    }
 
    public boolean isValid() {
        return validationError().isEmpty();
    }
 
}

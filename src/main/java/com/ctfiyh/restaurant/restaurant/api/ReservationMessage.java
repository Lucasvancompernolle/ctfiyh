/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.api;

import java.time.LocalDateTime;
import java.util.Optional;

import com.ctfiyh.restaurant.restaurant.shared.ReservationRepresentation;

/**
 *
 * @author lucas
 */
public record ReservationMessage(String reservationTime, String customerName, int numberOfGuests) {

    public ReservationMessage {

    }

    public Optional<Exception> validateMessage() {
        try {
            if (reservationTime == null) {
                throw new IllegalArgumentException("Reservation time cannot be null");
            }
            if (customerName == null || customerName.isBlank()) {
                throw new IllegalArgumentException("Customer name cannot be null or blank");
            }
            if (numberOfGuests <= 0) {
                throw new IllegalArgumentException("Number of guests must be greater than zero");
            }
            LocalDateTime.parse(reservationTime);
            return Optional.empty();

        } catch (Exception e) {
            return Optional.of(e);
        }

    }

    public static class Builder implements ReservationRepresentation<ReservationMessage> {
        private String dateTime;
        private String name;
        private int partySize;

        public Builder withDateTime(String dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder withCustomerName(String name) {
            this.name = name;
            return this;
        }

        public Builder withNumberOfPeople(int count) {
            this.partySize = count;
            return this;
        }

        @Override
        public ReservationMessage build() {
            return new ReservationMessage(dateTime, name, partySize);
        }

    }

    @Override
    public String toString() {
        return "ReservationMessage{"
                + "reservationTime=" + reservationTime
                + ", customerName=" + customerName +
                ", numberOfGuests=" + numberOfGuests + '}';
    }

}

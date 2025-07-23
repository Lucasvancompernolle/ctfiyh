/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.api;

import java.time.LocalDateTime;

import com.ctfiyh.restaurant.restaurant.shared.ReservationRepresentation;

/**
 *
 * @author lucas
 */
public record ReservationMessage(LocalDateTime reservationTime, String customerName, int numberOfGuests) {

    public ReservationMessage {
        if (reservationTime == null || customerName == null || numberOfGuests <= 0) {
            throw new IllegalArgumentException("Invalid reservation details");
        }
    }

    public static class Builder implements ReservationRepresentation<ReservationMessage> {
        private LocalDateTime dateTime;
        private String name;
        private int partySize;

        public Builder withDateTime(LocalDateTime dateTime) {
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
        return "ReservationMessage{" +
                "reservationTime=" + reservationTime +
                ", customerName='" + customerName + '\'' +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }

}

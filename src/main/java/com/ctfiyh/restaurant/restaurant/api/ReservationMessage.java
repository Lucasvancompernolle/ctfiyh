/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.api;

import java.time.LocalDateTime;

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

    @Override
    public String toString() {
        return "ReservationMessage{" +
                "reservationTime=" + reservationTime +
                ", customerName='" + customerName + '\'' +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }

}

package com.ctfiyh.restaurant.restaurant.domain;

import com.ctfiyh.restaurant.restaurant.api.ReservationMessage;

public class InvalidReservation implements ReservationResult<ReservationMessage> {

    private String message;

    public InvalidReservation(String message) {
        this.message = message;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}

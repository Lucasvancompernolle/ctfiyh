package com.ctfiyh.restaurant.restaurant.domain;

import com.ctfiyh.restaurant.restaurant.api.ReservationMessage;

public class ValidReservation implements ReservationResult<ReservationMessage> {
 
    private String message;

    public ValidReservation(String message) {
        this.message = message;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}

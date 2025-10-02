package com.ctfiyh.restaurant.restaurant.domain;

public interface ReservationResult<T> {

    boolean isValid();

    String getMessage();

}

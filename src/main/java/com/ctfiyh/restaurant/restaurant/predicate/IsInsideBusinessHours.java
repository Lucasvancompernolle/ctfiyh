package com.ctfiyh.restaurant.restaurant.predicate;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ctfiyh.restaurant.restaurant.api.ReservationMessage;

@Component
public class IsInsideBusinessHours implements Predicate<ReservationMessage> {

    @Value("${restaurant.openingHour:10}")
    private final int openingHour = 10; // 10 AM

    @Value("${restaurant.closingHour:22}")
    private final int closingHour = 22; // 10 PM

    @Override
    public boolean test(ReservationMessage reservationMessage) {

        LocalDateTime reservationTime = LocalDateTime.parse(reservationMessage.reservationTime());
        int hour = reservationTime.getHour();

        return hour >= openingHour && hour < closingHour;

    }

}

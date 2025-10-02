package com.ctfiyh.restaurant.restaurant.predicate;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ctfiyh.restaurant.restaurant.api.ReservationMessage; 
import com.ctfiyh.restaurant.restaurant.repository.ReservationRepository;

@Component
public class HasAvailableSeats implements Predicate<ReservationMessage> {

    @Value("${restaurant.totalSeats:10}")
    private int totalSeats;
    
    private final ReservationRepository reservationRepository; 

    public HasAvailableSeats(ReservationRepository reservationRepository) { 
        this.reservationRepository = reservationRepository;
    }

    @Override
    public boolean test(ReservationMessage reservationMessage) {
         
        return (this.totalSeats - numberOfSeatsAlreadyReservedToday()) >= reservationMessage.numberOfGuests();
    }

    private int numberOfSeatsAlreadyReservedToday() {
        return this.reservationRepository.findByReservationTimeRange(
            LocalDateTime.now(), 
            LocalDateTime.now().plusDays(1))
                .stream()
                .mapToInt(reservation -> reservation.numberOfPeople())
                .sum();
    }

}

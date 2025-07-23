/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.repository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.ctfiyh.restaurant.restaurant.TestcontainersConfiguration;
import com.ctfiyh.restaurant.restaurant.api.ReservationMessage;
import com.ctfiyh.restaurant.restaurant.domain.Reservation;

/**
 *
 * @author lucas
 */

@SpringBootTest
@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
class ReservationRepositoryIT {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void shouldSaveReservation() {
        Reservation reservation = new Reservation(LocalDateTime.now(), "John Doe", 4);

        this.reservationRepository.save(reservation);

        assertThat(reservationRepository.findByCustomerName("John Doe")).isNotEmpty();
    }

    @Test
    void shouldFindReservationByCustomerName() {
        Reservation reservation = new Reservation(LocalDateTime.now(), "Jane Doe", 2);
        ReservationMessage message = reservation.writeTo(new ReservationMessage.Builder());

        this.reservationRepository.save(reservation);
        var foundReservation = reservationRepository.findByCustomerName("Jane Doe");

        foundReservation.ifPresent(reservationFromDb -> {
            ReservationMessage messageToCheck = reservationFromDb.writeTo(new ReservationMessage.Builder());
            assertThat(messageToCheck.reservationTime()).isEqualTo(message.reservationTime());
            assertThat(messageToCheck.customerName()).isEqualTo(message.customerName());
            assertThat(messageToCheck.numberOfGuests()).isEqualTo(message.numberOfGuests());
        });
    }

}

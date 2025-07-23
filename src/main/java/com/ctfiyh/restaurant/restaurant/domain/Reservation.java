/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.domain;

import java.time.LocalDateTime;

import com.ctfiyh.restaurant.restaurant.shared.ReservationRepresentation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    private Long id;
    private LocalDateTime reservationTime;
    private String customerName;
    private int numberOfGuests;

    public Reservation(LocalDateTime reservationTime, String customerName, int numberOfGuests) {
        this.reservationTime = reservationTime;
        this.customerName = customerName;
        this.numberOfGuests = numberOfGuests;
    }

    public <T> T writeTo(ReservationRepresentation<T> representation) {
        return representation
                .withDateTime(this.reservationTime)
                .withCustomerName(this.customerName)
                .withNumberOfPeople(numberOfGuests)
                .build();
    }

}

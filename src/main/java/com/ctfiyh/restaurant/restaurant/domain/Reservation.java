/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.domain;

import java.time.LocalDateTime;

import com.ctfiyh.restaurant.restaurant.shared.ReservationRepresentation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime reservationTime;
    private String customerName;
    private int numberOfPeople;

    private Reservation() {
        // Default constructor for Hibernate
    }

    public Reservation(LocalDateTime reservationTime, String customerName, int numberOfPeople) {
        this.reservationTime = reservationTime;
        this.customerName = customerName;
        this.numberOfPeople = numberOfPeople;
    }

    public <T> T writeTo(ReservationRepresentation<T> representation) {
        return representation
                .withDateTime(this.reservationTime.toString())
                .withCustomerName(this.customerName)
                .withNumberOfPeople(this.numberOfPeople)
                .build();
    }

    public int numberOfPeople() {
        return this.numberOfPeople;
    }

}

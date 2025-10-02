/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.service;

import java.util.List;
import java.time.LocalDateTime; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ctfiyh.restaurant.restaurant.api.ReservationMessage;
import com.ctfiyh.restaurant.restaurant.api.ReservationMessageValidator;
import com.ctfiyh.restaurant.restaurant.domain.InvalidReservation;
import com.ctfiyh.restaurant.restaurant.domain.Reservation;
import com.ctfiyh.restaurant.restaurant.domain.ReservationResult;
import com.ctfiyh.restaurant.restaurant.domain.ValidReservation;
import com.ctfiyh.restaurant.restaurant.predicate.HasAvailableSeats;
import com.ctfiyh.restaurant.restaurant.predicate.IsInsideBusinessHours;
import com.ctfiyh.restaurant.restaurant.repository.ReservationRepository; 

import jakarta.transaction.Transactional;

/**
 *
 * @author lucas
 */
@Service
@Transactional
public class ReservationService {

    private final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    
    private final ReservationRepository reservationRepository;
    private final HasAvailableSeats hasAvailableSeats;
    private final IsInsideBusinessHours isInsideBusinessHours;

    public ReservationService(ReservationRepository reservationRepository,
            HasAvailableSeats hasAvailableSeats,
            IsInsideBusinessHours isInsideBusinessHours) {
        this.reservationRepository = reservationRepository;
        this.hasAvailableSeats = hasAvailableSeats;
        this.isInsideBusinessHours = isInsideBusinessHours;
    }

    public List<ReservationMessage> getReservationsByCustomerName(String customerName) {

        return this.reservationRepository.findByCustomerName(customerName).stream()
                .map(reservation -> reservation.writeTo(new ReservationMessage.Builder()))
                .toList();
    }

    public ReservationResult<ReservationMessage> createReservation(ReservationMessage reservationMessage) {

        ReservationMessageValidator messageValidator = new ReservationMessageValidator(reservationMessage);
         
        
        if (!messageValidator.isValid()) {
            return new InvalidReservation(messageValidator.validationError().get());
        }

        if(!this.isInsideBusinessHours.test(reservationMessage)) {
            return new InvalidReservation("Reservation time is outside business hours (10:00 - 22:00)");
        }

        if (!this.hasAvailableSeats.test(reservationMessage)) {
            return new InvalidReservation("Not enough available seats for the requested reservation");
        }

        this.reservationRepository.save(new Reservation(
                LocalDateTime.parse(reservationMessage.reservationTime()),
                reservationMessage.customerName(),
                reservationMessage.numberOfGuests()));

        this.logger.info("Creating reservation for {}", reservationMessage);

        return new ValidReservation("Reservation created successfully");

    }

    public List<ReservationMessage> getReservationsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return this.reservationRepository.findByReservationTimeRange(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1))
                .stream()
                .map(reservation -> reservation.writeTo(new ReservationMessage.Builder()))
                .toList();
    }

    public int getReservedSeatsOn(LocalDateTime dateTime) {
        return this.reservationRepository.countReservedSeatsOn(dateTime);
    }

}

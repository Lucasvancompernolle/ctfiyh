/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ctfiyh.restaurant.restaurant.api.ReservationMessage;
import com.ctfiyh.restaurant.restaurant.domain.Reservation;
import com.ctfiyh.restaurant.restaurant.repository.ReservationRepository;

/**
 *
 * @author lucas
 */
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void createReservation(ReservationMessage reservationMessage) {

        this.reservationRepository.save(new Reservation(
                reservationMessage.reservationTime(),
                reservationMessage.customerName(),
                reservationMessage.numberOfGuests()));

        this.logger.info("Creating reservation for {}", reservationMessage);
    }

}

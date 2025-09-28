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
import com.ctfiyh.restaurant.restaurant.domain.Reservation;
import com.ctfiyh.restaurant.restaurant.repository.ReservationRepository;

import jakarta.transaction.Transactional;

/**
 *
 * @author lucas
 */
@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationMessage> getReservationsByCustomerName(String customerName) {

        return this.reservationRepository.findByCustomerName(customerName).stream()
                .map(reservation -> reservation.writeTo(new ReservationMessage.Builder()))
                .toList();
    }

    public void createReservation(ReservationMessage reservationMessage) {

        this.reservationRepository.save(new Reservation(
                LocalDateTime.parse(reservationMessage.reservationTime()),
                reservationMessage.customerName(),
                reservationMessage.numberOfGuests()));

        this.logger.info("Creating reservation for {}", reservationMessage);
    }

    public List<ReservationMessage> getReservationsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return this.reservationRepository.findByReservationTimeRange(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1))
                .stream()
                .map(reservation -> reservation.writeTo(new ReservationMessage.Builder()))
                .toList();
    }

}

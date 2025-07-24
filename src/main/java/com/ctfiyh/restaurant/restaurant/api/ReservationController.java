/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctfiyh.restaurant.restaurant.service.ReservationService;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/customers/{customerName}")
    public ResponseEntity<List<ReservationMessage>> getReservationsByCustomerName(@PathVariable String customerName) {
        return ResponseEntity.ok(this.reservationService.getReservationsByCustomerName(customerName));
    }

    @PostMapping()
    public ResponseEntity<Void> createReservation(@RequestBody ReservationMessage entity) {
        this.reservationService.createReservation(entity);
        return ResponseEntity.noContent().build();
    }

}

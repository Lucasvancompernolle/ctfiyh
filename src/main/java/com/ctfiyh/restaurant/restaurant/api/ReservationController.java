/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.api;

import java.util.List;

import org.slf4j.Logger;
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
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/customers/{customerName}")
    public ResponseEntity<List<ReservationMessage>> getReservationsByCustomerName(@PathVariable String customerName) {
        return ResponseEntity.ok(this.reservationService.getReservationsByCustomerName(customerName));
    }

    @PostMapping()
    public ResponseEntity<String> createReservation(@RequestBody ReservationMessage entity) {
        var validationResult = entity.validateMessage();

        if (validationResult.isPresent()) {
            logger.error("Validation error: {}", validationResult.get().getMessage());
            return ResponseEntity.badRequest().body(validationResult.get().getMessage());
        }

        this.reservationService.createReservation(entity);
        
        return ResponseEntity.ok().build();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.repository;

import java.util.Optional;

import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ctfiyh.restaurant.restaurant.domain.Reservation;

/**
 *
 * @author lucas
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @SQL("SELECT * FROM reservations WHERE customer_name = ?1")
    Optional<Reservation> findByCustomerName(String customerName);

}

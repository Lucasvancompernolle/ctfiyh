/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ctfiyh.restaurant.restaurant.domain.Reservation;

/**
 *
 * @author lucas
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @SQL("SELECT * FROM reservation WHERE customer_name = ?1")
    List<Reservation> findByCustomerName(String customerName);

    @Query(value = "SELECT * FROM reservation WHERE reservation_time >= ?1 AND reservation_time <= ?2", nativeQuery = true)
    List<Reservation> findByReservationTimeRange(LocalDateTime minReservationTime, LocalDateTime maxReservationTime);

    @Query(value = "SELECT SUM(number_of_people) FROM reservation WHERE reservation_time = ?1", nativeQuery = true)
    int countReservedSeatsOn(LocalDateTime dateTime);

}

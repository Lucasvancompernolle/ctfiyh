/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.shared;

import java.time.LocalDateTime;

/**
 *
 * @author lucas
 */
public interface ReservationRepresentation<T> {

    public ReservationRepresentation<T> withDateTime(LocalDateTime dateTime);

    public ReservationRepresentation<T> withCustomerName(String customerName);

    public ReservationRepresentation<T> withNumberOfPeople(int numberOfPeople);

    public T build();

}

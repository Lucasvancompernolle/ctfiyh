/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.api;
 
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc; 
import org.springframework.context.annotation.Import;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ctfiyh.restaurant.restaurant.RestaurantApplicationTests;
import com.ctfiyh.restaurant.restaurant.TestcontainersConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
/**
 *
 * @author lucas
 */
 
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class) 
public class ReservationControllerIT extends RestaurantApplicationTests{

    private static final String RESERVATION_ENDPOINT = "/api/reservations";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest()
    @CsvSource({
        "2025-09-15T14:23:45, John Doe, 4",
        "2025-09-16T10:00:00, Alice, 2"
    })
    public void shouldSucceedInCreatingReservation(String time, String name, int guests) throws Exception {
        System.out.println("Testing with: " + aReservationMessage(time, name, guests));
        mockMvc.perform(post(RESERVATION_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(aReservationMessage(time, name, guests)))
                .andExpect(status().isOk());

    }

    @ParameterizedTest()
    @CsvSource({
        "20250915T142345, John Doe, 4",
        "2025-09-16, Alice, -5",
        "10:00:00, , 3", 
        ", Alice, 10", 
    })
    public void shouldFailInCreatingReservation(String time, String name, int guests) throws Exception {
        System.out.println("Testing with: " + aReservationMessage(time, name, guests));
        mockMvc.perform(post(RESERVATION_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(aReservationMessage(time, name, guests)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> System.out.println("Response: " + result.getResponse().getContentAsString()));

    }

    private String aReservationMessage(String time, String name, int guests) throws JsonProcessingException {
        return objectMapper.writeValueAsString(new ReservationMessage.Builder()
        .withDateTime(time)
        .withCustomerName(name)
        .withNumberOfPeople(guests)
        .build());
    }

}
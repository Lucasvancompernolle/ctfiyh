/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.ctfiyh.restaurant.restaurant.api;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testcontainers.utility.TestcontainersConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author lucas
 */
@Import(TestcontainersConfiguration.class)
@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ReservationControllerIT {

    private static final String RESERVATION_ENDPOINT = "/api/reservations/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateReservation() throws Exception {

        mockMvc.perform(post(RESERVATION_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(aReservationMessage()))
                .andExpect(
                        status().isOk());

    }

    private String aReservationMessage() throws JsonProcessingException {
        return objectMapper.writeValueAsString(new ReservationMessage(LocalDateTime.now(), "John Doe", 4));
    }

}
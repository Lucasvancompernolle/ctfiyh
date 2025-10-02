package com.ctfiyh.restaurant.restaurant.exception;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
 

public class HandleInvalidReservationException {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(HandleInvalidReservationException.class);
   
    @Bean
    @ExceptionHandler(InvalidReservationException.class)
    public ResponseEntity<String> handleValidationException(InvalidReservationException ex) {
        logger.warn("Validation failed: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}

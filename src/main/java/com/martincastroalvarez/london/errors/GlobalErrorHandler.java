package com.martincastroalvarez.london;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalErrorHandler {
    // --------------------------------------------------------------------
    // Global exception handler responsbile for translating Java errors
    // into JSON messages that can be returned by the API controllers.
    // --------------------------------------------------------------------
    //
    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        logger.error("Error | Handler: " + ex + " " + request);
        ErrorResponse response = new ErrorResponse(ex.getClass().getName(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

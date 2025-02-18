package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle 400 BAD REQUEST for validation errors (specific field messages)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Check for specific fields and provide custom error messages
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            if ("name".equals(error.getField())) {
                errors.put("name", "Name cannot be empty");
            } else if ("amount".equals(error.getField())) {
                errors.put("amount", "Amount cannot be null");
            } else {
                // Default error message for other fields
                errors.put(error.getField(), error.getDefaultMessage());
            }
        }

        // Return 400 BAD REQUEST with the customized error messages
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handle 500 INTERNAL SERVER ERROR for general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternalServerErrors(Exception ex) {
        return new ResponseEntity<>("Sorry, we ran into an error, please check back!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle custom exceptions (Optional, if you have custom exceptions)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleCustomErrors(ResponseStatusException ex) {
        return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
    }
}


package com.example.ordersystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice // This annotation allows us to handle exceptions across the whole application
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleCloudVendorNotFoundException
            (NotFoundException notFoundException)
    {
        Exception Exception = new Exception(
                notFoundException.getMessage(),
                notFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(Exception, HttpStatus.NOT_FOUND);
    }
}

package com.example.ordersystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for handling exceptions across the whole application.
 * It uses the @ControllerAdvice annotation to achieve this.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method handles NotFoundException.
     * It returns a ResponseEntity with the exception message and HTTP status.
     *
     * @param notFoundException the exception to handle
     * @return a ResponseEntity with the exception message and HTTP status
     */
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> NotFoundException
            (NotFoundException notFoundException)
    {
        Exception Exception = new Exception(
                notFoundException.getMessage(),
                notFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(Exception, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles CustomHttpException.
     * It returns a ResponseEntity with the exception message and HTTP status.
     *
     * @param httpException the exception to handle
     * @return a ResponseEntity with the exception message and HTTP status
     */
    @ExceptionHandler(value= {CustomHttpException.class})
    public ResponseEntity<Object> HttpException
            (CustomHttpException httpException)
    {
        Exception Exception = new Exception(
                httpException.getMessage(),
                httpException.getCause(),
                httpException.getHttpStatus()
        );

        return new ResponseEntity<>(Exception, httpException.getHttpStatus());
    }

    /**
     * This method handles MethodArgumentNotValidException.
     * It returns a ResponseEntity with the validation errors and HTTP status.
     *
     * @param ex the exception to handle
     * @return a ResponseEntity with the validation errors and HTTP status
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
package com.aniad.flashcardbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ExistingUserException.class})
    public ResponseEntity<Object> handleException(ExistingUserException e){
        HttpStatus status = HttpStatus.CONFLICT;
        ApiException exception = new ApiException(e.getMessage(),status, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(exception,status);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleException(IllegalArgumentException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(e.getMessage(),status, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(exception,status);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleException(UserNotFoundException e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiException exception = new ApiException(e.getMessage(),status, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(exception,status);
    }

}

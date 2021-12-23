package org.ptit.controller.advice;


import org.ptit.exception.ServiceError;
import org.ptit.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class UserControllerAdvice  {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        ServiceError error = new ServiceError("Not found");
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(ConstraintViolationException ex) {
        ServiceError error=new ServiceError("Validation Failed");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}

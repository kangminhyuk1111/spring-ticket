package com.ticket.exception;

import com.ticket.exception.member.NotFoundMemberException;
import com.ticket.exception.member.UnauthorizedAccessException;
import com.ticket.exception.member.WrongPasswordException;
import com.ticket.exception.payment.InsufficientBalanceException;
import com.ticket.exception.performance.AlreadyExistPerformanceException;
import com.ticket.exception.performance.NotFoundPerformanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.AlreadyBoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundMemberException.class)
    public ResponseEntity<String> handleInvalidUserException(NotFoundMemberException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> handleWrongPasswordException(WrongPasswordException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyBoundException.class)
    public ResponseEntity<String> handleAlreadyBoundException(AlreadyBoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundPerformanceException.class)
    public ResponseEntity<String> handleNotFoundPerformanceException(NotFoundPerformanceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistPerformanceException.class)
    public ResponseEntity<String> handleAlreadyExistPerformanceException(AlreadyExistPerformanceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> handleInsufficientBalanceException(InsufficientBalanceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<String> handleUnauthorizedAccessException(UnauthorizedAccessException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

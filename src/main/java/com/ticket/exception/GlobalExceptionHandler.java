package com.ticket.exception;

import com.ticket.exception.errcode.ErrorCode;
import com.ticket.exception.member.NotFoundMemberException;
import com.ticket.exception.member.UnauthorizedAccessException;
import com.ticket.exception.member.WrongPasswordException;
import com.ticket.exception.payment.InsufficientBalanceException;
import com.ticket.exception.performance.AlreadyExistPerformanceException;
import com.ticket.exception.performance.NotFoundPerformanceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.rmi.AlreadyBoundException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
        String message;

        switch (ex.getStatusCode()) {
            case BAD_REQUEST:
                message = ErrorCode.CLIENT_ERROR_400.getMessage();
                break;
            case UNAUTHORIZED:
                message = ErrorCode.CLIENT_ERROR_401.getMessage();
                break;
            case NOT_FOUND:
                message = ErrorCode.CLIENT_ERROR_404.getMessage();
                break;
            default:
                message = "Unknown Client Error";
        }

        return new ResponseEntity<>(message, ex.getStatusCode());
    }

//    @ExceptionHandler(HttpClientErrorException.class)
//    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
//        HttpStatus status = (HttpStatus) ex.getStatusCode();
//        String message = ErrorCode.CLIENT_ERROR_400.getMessage();
//        return new ResponseEntity<>(message, status);
//    }
//
//    @ExceptionHandler(HttpServerErrorException.class)
//    public ResponseEntity<String> handleHttpServerErrorException(HttpServerErrorException ex) {
//        HttpStatus status = (HttpStatus) ex.getStatusCode();
//        String message = ErrorCode.SERVER_ERROR_500.getMessage();
//        return new ResponseEntity<>(message, status);
//    }

    @ExceptionHandler(NotFoundMemberException.class)
    public ResponseEntity<String> handleInvalidUserException(NotFoundMemberException e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> handleWrongPasswordException(WrongPasswordException e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyBoundException.class)
    public ResponseEntity<String> handleAlreadyBoundException(AlreadyBoundException e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundPerformanceException.class)
    public ResponseEntity<String> handleNotFoundPerformanceException(NotFoundPerformanceException e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistPerformanceException.class)
    public ResponseEntity<String> handleAlreadyExistPerformanceException(AlreadyExistPerformanceException e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> handleInsufficientBalanceException(InsufficientBalanceException e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<String> handleUnauthorizedAccessException(UnauthorizedAccessException e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }
}

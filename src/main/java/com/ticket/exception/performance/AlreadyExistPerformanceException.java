package com.ticket.exception.performance;

public class AlreadyExistPerformanceException extends RuntimeException{

    public AlreadyExistPerformanceException() {
        super("이미 존재하는 공연정보 입니다.");
    }
}

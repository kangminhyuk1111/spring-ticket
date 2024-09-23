package com.ticket.exception.performance;

public class NotFoundPerformanceException extends RuntimeException {

    public NotFoundPerformanceException() {
        super("존재하지 않는 공연정보 입니다.");
    }
}

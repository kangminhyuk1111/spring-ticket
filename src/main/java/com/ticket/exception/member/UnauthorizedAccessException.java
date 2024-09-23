package com.ticket.exception.member;

public class UnauthorizedAccessException extends RuntimeException{

    public UnauthorizedAccessException() {
        super("권한이 없는 사용자 입니다.");
    }
}

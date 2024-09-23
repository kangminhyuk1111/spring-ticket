package com.ticket.exception.token;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException() {
        super("토큰 정보가 일치하지 않습니다.");
    }
}

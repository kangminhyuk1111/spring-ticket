package com.ticket.exception.member;

public class NotFoundMemberException extends RuntimeException{

    public NotFoundMemberException(String message) {
        super(message);
    }
}

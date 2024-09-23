package com.ticket.exception.member;

public class NotFoundMemberException extends RuntimeException{

    public NotFoundMemberException() {
        super("존재하지 않는 ID입니다.");
    }
}

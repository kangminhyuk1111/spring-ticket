package com.ticket.exception.member;

public class AlreadyExistMemberException extends RuntimeException{

    public AlreadyExistMemberException() {
        super("이미 존재하는 사용자 ID입니다.");
    }
}

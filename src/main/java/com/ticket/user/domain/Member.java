package com.ticket.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    private String userId;
    private String userPw;

    public Member() {}

    public Member(String userId, String userPw) {
        this.userId = userId.trim();
        this.userPw = userPw.trim();
    }

    public boolean checkPassword(String pw) {
        return this.userPw.equals(pw);
    }
}

package com.ticket.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private String userId;
    private String userPw;

    public User() {}

    public User(String userId, String userPw) {
        this.userId = userId.trim();
        this.userPw = userPw.trim();
    }

    public boolean checkPassword(String password) {
        return this.userPw.equals(password);
    }
}

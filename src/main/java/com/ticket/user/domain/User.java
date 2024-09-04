package com.ticket.user.domain;

public class User {

    private String id;
    private String pw;

    public User(String userId, String userPw) {
        this.id = userId.trim();
        this.pw = userPw.trim();
    }

    public boolean checkPassword(String password) {
        return this.pw.equals(password);
    }
}

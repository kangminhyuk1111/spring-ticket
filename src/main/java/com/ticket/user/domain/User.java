package com.ticket.user.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String userId;
    private String pw;

    public User(String userId, String userPw) {
        this.userId = userId.trim();
        this.pw = userPw.trim();
    }

    public User() {

    }

    public boolean checkPassword(String password) {
        return this.pw.equals(password);
    }
}

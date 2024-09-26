package com.ticket.token.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Token {

    @Id
    private String token;
    private String userId;
    private LocalDateTime expirationTime;

    public Token(final Token token, final String userId, final LocalDateTime now) {
    }

    public Token(String token, String userId, LocalDateTime expirationTime) {
        this.token = token;
        this.userId = userId;
        this.expirationTime = expirationTime;
    }

    public boolean isExpired() {
        return expirationTime.isBefore(LocalDateTime.now());
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }
}


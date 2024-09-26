package com.ticket.token.provider;

import com.ticket.token.domain.Token;

import java.time.LocalDateTime;
import java.util.UUID;

public class FakeTokenProvider implements TokenProvider {
    @Override
    public Token createToken(final String userId) {
        String uuid = UUID.randomUUID().toString();
        LocalDateTime expiresTime = LocalDateTime.now().plusHours(1);
        return new Token(uuid, userId, expiresTime);
    }
}

package com.ticket.token.provider;

import com.ticket.token.domain.Token;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UUIDTokenProvider implements TokenProvider {

    @Override
    public Token createToken(String userId) {
        String uuid = UUID.randomUUID().toString();
        LocalDateTime expiresTime = LocalDateTime.now().plusHours(1);
        return new Token(uuid, userId, expiresTime);
    }
}

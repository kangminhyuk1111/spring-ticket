package com.ticket.token.provider;

import com.ticket.token.domain.Token;

import java.time.LocalDateTime;

public interface TokenProvider {

    Token createToken(String userId);
}

package com.ticket.token.repository;

import com.ticket.token.domain.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeTokenRepository implements TokenRepository {

    List<Token> tokens = new ArrayList<>();

    @Override
    public Optional<Token> findByUserId(final String userId) {
        return tokens.stream()
                .filter(token -> token.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public Token save(final Token token) {
        tokens.add(token);

        return token;
    }

    @Override
    public void deleteAll() {
        tokens.clear();
    }
}

package com.ticket.token.repository;

import com.ticket.token.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TokenRepository {

    Optional<Token> findByUserId(String userId);

    Token save(Token token);

    void deleteAll();
}

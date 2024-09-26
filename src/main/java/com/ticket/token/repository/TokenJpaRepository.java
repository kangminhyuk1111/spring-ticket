package com.ticket.token.repository;

import com.ticket.token.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenJpaRepository extends JpaRepository<Token, Long>, TokenRepository {
}

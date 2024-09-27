package com.ticket.ticket.repository;

import com.ticket.ticket.domain.Ticket;

import java.util.Optional;

public interface TicketRepository {
    Ticket save(Ticket ticket);

    Optional<Ticket> findByMemberUserId(String userId);
}

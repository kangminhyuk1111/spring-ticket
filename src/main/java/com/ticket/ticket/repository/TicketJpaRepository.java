package com.ticket.ticket.repository;

import com.ticket.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJpaRepository extends JpaRepository<Ticket, Long>, TicketRepository {
}

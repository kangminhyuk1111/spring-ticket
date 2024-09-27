package com.ticket.ticket.repository;

import com.ticket.ticket.domain.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeTicketRepository implements TicketRepository {

    private List<Ticket> tickets = new ArrayList<>();
    private Long counter = 1L;

    @Override
    public Ticket save(final Ticket ticket) {
        ticket.setId(counter++);
        tickets.add(ticket);

        return ticket;
    }

    public Optional<Ticket> findByMemberUserId(final String userId) {
        return tickets.stream()
                .filter(ticket -> ticket.getMember().getUserId().equals(userId))
                .findFirst();
    }
}

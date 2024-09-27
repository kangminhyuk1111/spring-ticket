package com.ticket.ticket.domain;

import com.ticket.performance.domain.Performance;
import com.ticket.user.domain.Member;
import jakarta.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    private Long price;
    private String cardNumber;
    private String transactionId;

    public Ticket(Performance performance, Member member, Long price, String cardNumber) {
        this.performance = performance;
        this.member = member;
        this.price = price;
    }

    public Ticket() {
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public Long getId() {
        return id;
    }
}
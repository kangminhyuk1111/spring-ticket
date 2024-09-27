package com.ticket.payment.domain;

import com.ticket.exception.payment.InsufficientBalanceException;
import com.ticket.user.domain.Member;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Member member;
    private BigDecimal balance;

    public Wallet() {}

    public Wallet(Member member, BigDecimal balance) {
        this.member = member;
        this.balance = balance;
    }

    // 잔액 증가
    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    // 잔액 감소
    public void withdraw(BigDecimal amount) {
        isPaymentable(amount);
        this.balance = this.balance.subtract(amount);
    }

    // 환불
    public void refund(BigDecimal amount) {
        deposit(amount);
    }

    // 잔액 충전
    public void chargeAmount(BigDecimal amount) {
        deposit(amount);
    }

    private void isPaymentable(final BigDecimal ticketPrice) {
        if (this.balance.compareTo(ticketPrice) < 0) {
            throw new InsufficientBalanceException();
        }
    }
}

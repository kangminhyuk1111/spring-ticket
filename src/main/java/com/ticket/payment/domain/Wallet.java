package com.ticket.payment.domain;

import com.ticket.exception.payment.InsufficientBalanceException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private BigDecimal balance;

    public Wallet() {}

    public Wallet(String userId, BigDecimal balance) {
        this.userId = userId;
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

    // 유저 id 검증
    public boolean isUserIdMatch(String userId) {
        return this.userId.equals(userId);
    }

    private void isPaymentable(final BigDecimal ticketPrice) {
        if (this.balance.compareTo(ticketPrice) < 0) {
            throw new InsufficientBalanceException();
        }
    }
}

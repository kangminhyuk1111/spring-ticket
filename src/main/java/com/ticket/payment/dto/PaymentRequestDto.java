package com.ticket.payment.dto;

import java.math.BigDecimal;

public class PaymentRequestDto {

    private BigDecimal amount;
    private String cardNumber;

    public PaymentRequestDto() {
    }

    public PaymentRequestDto(final BigDecimal amount, final String cardNumber) {
        this.amount = amount;
        this.cardNumber = cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}

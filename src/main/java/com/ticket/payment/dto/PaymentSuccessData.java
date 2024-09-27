package com.ticket.payment.dto;

public class PaymentSuccessData {

    private String message;
    private String transactionId;

    public PaymentSuccessData(final String message, final String transactionId) {
        this.message = message;
        this.transactionId = transactionId;
    }
}

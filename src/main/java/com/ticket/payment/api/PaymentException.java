package com.ticket.payment.api;

import org.springframework.web.client.HttpStatusCodeException;

public class PaymentException extends RuntimeException {
    private String message;

    public PaymentException(final Exception e) {

        if (e instanceof HttpStatusCodeException ex) {
            message = "Client Error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString();
        }

        // 기타 예외 처리
        System.err.println("An unexpected error occurred: " + e.getMessage());
    }
}

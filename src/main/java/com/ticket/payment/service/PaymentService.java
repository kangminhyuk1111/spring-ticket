package com.ticket.payment.service;

import com.ticket.payment.api.PaymentApi;
import com.ticket.payment.dto.PaymentResponseDto;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentApi paymentApi;

    public PaymentService(final PaymentApi paymentApi) {
        this.paymentApi = paymentApi;
    }

    public void processPayment(String cardNumber, Long ticketPrice) {
        final PaymentResponseDto paymentResponse = paymentApi.makePayment(cardNumber, ticketPrice);

        System.out.println(paymentResponse.toString());
    }
}

package com.ticket.payment.api;

import com.ticket.payment.dto.PaymentResponseDto;

public class FakePaymentApi implements PaymentApi{
    @Override
    public PaymentResponseDto makePayment(final String cardNumber, final Long ticketPrice) {
        if ("1234-1234-1234-1234".equals(cardNumber) && ticketPrice <= 10000) {
            return new PaymentResponseDto();
        } else if (ticketPrice > 10000) {
            return new PaymentResponseDto();
        }
        return new PaymentResponseDto();
    }
}

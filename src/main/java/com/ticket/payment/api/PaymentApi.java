package com.ticket.payment.api;

import com.ticket.payment.dto.PaymentResponseDto;

public interface PaymentApi {

    PaymentResponseDto makePayment(String cardNumber, Long ticketPrice);
}

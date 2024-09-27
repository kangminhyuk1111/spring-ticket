package com.ticket.payment.controller;

import com.ticket.api.ApiResponse;
import com.ticket.payment.dto.PaymentRequestDto;
import com.ticket.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ApiResponse payment(@RequestBody final PaymentRequestDto dto) {
        paymentService.processPayment(dto.getCardNumber(), dto.getAmount().longValue());
        return new ApiResponse(HttpStatus.OK, "success", null);
    }
}

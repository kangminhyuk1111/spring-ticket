package com.ticket.payment.api;

import com.ticket.payment.dto.PaymentRequestDto;
import com.ticket.payment.dto.PaymentResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class RestTemplatePaymentApi implements PaymentApi {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplatePaymentApi.class);
    private final RestTemplate restTemplate;

    public RestTemplatePaymentApi(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PaymentResponseDto makePayment(final String cardNumber, final Long ticketPrice) {
        // PaymentRequestDto 객체 생성
        PaymentRequestDto dto = new PaymentRequestDto(BigDecimal.valueOf(ticketPrice), cardNumber);

        // HTTP POST 요청
        try {
            ResponseEntity<PaymentResponseDto> responseEntity =
                    restTemplate.postForEntity("http://localhost:8081/payment", dto, PaymentResponseDto.class);

            // 응답 반환
            return responseEntity.getBody();
        } catch (Exception e) {
            throw new PaymentException(e);
        }
    }
}

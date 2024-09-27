package com.ticket.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentResponseDto<T> {

    @JsonProperty("statusCode")
    private int statusCode;

    @JsonProperty("data")
    private T data;

    public PaymentResponseDto() {
    }

    public PaymentResponseDto(final T data) {
        this.data = data;
    }

    public PaymentResponseDto(final int statusCode, final T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getData() {
        return data;
    }

    // 내부 클래스 정의 (필요에 따라 분리 가능)
    public static class PaymentData {
        private String message;
        private String transactionId;

        // Getters and setters
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }
    }

    @Override
    public String toString() {
        return "PaymentResponseDto{" +
                "statusCode=" + statusCode +
                ", data=" + data +
                '}';
    }
}

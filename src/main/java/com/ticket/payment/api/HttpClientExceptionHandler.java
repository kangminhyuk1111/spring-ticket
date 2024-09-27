package com.ticket.payment.api;

import org.springframework.web.client.HttpClientErrorException;

public class HttpClientExceptionHandler implements HttpErrorExceptionHandler {
    @Override
    public boolean support(final Exception e) {
        return e instanceof HttpClientErrorException;
    }

    @Override
    public <T extends Exception> void handle(final T exception) {
        HttpClientErrorException clientException = (HttpClientErrorException) exception;
        System.err.println("Client Error: " + clientException.getStatusCode() + " - " + clientException.getResponseBodyAsString());
    }
}

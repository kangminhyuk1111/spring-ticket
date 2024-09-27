package com.ticket.payment.api;

import org.springframework.web.client.HttpServerErrorException;

public class HttpServerExceptionHandler implements HttpErrorExceptionHandler{
    @Override
    public boolean support(final Exception e) {
        return e instanceof HttpServerErrorException;
    }

    @Override
    public <T extends Exception> void handle(final T exception) {
        HttpServerErrorException serverException = (HttpServerErrorException) exception;
        System.err.println("Server Error: " + serverException.getStatusCode() + " - " + serverException.getResponseBodyAsString());
    }
}

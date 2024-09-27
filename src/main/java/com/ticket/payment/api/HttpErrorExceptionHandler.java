package com.ticket.payment.api;

public interface HttpErrorExceptionHandler {
    boolean support(Exception e);

    <T extends Exception> void handle(T exception);
}

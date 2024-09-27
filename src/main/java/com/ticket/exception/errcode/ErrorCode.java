package com.ticket.exception.errcode;

public enum ErrorCode {
    CLIENT_ERROR_400(400, "Client Error"),
    CLIENT_ERROR_401(401, "Unauthorized"),
    CLIENT_ERROR_404(404, "Not Found"),
    SERVER_ERROR_500(500, "Internal Server Error"),
    SERVER_ERROR_503(503, "Service Unavailable");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

package com.gameshub.Exception;

import org.springframework.http.*;

import java.time.*;

public class ErrorResponse {
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}

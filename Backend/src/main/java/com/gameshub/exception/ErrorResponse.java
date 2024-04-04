package com.gameshub.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ErrorResponse {

    private final String message;
    private final HttpStatus status;
    private final String timestamp;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd :: HH:mm:ss"));
    }

}

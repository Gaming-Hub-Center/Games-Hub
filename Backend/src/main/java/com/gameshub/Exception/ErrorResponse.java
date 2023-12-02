package com.gameshub.Exception;

import lombok.*;
import org.springframework.http.*;

import java.time.*;
import java.time.format.*;

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

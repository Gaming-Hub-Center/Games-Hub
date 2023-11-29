package com.gameshub.Exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class PasswordMismatchException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PasswordMismatchException(String message) {
        super(message);
    }

}

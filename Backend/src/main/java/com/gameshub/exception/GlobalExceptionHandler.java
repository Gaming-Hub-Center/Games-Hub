package com.gameshub.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormatException(InvalidFormatException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyFoundException(ResourceAlreadyFoundException ex) {
        HttpStatus httpStatus = HttpStatus.FOUND;
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatchException(PasswordMismatchException ex) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        HttpStatus httpStatus = HttpStatus.FOUND;
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ErrorResponse> handleOutOfStockException(OutOfStockException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleBalanceShortageException(InsufficientBalanceException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

}

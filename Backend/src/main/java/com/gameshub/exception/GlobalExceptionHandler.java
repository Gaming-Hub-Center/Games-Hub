package com.gameshub.exception;

<<<<<<< Updated upstream
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
=======
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
>>>>>>> Stashed changes

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), status);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatchException(PasswordMismatchException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ResourceAlreadyFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyFoundException(ResourceAlreadyFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.FOUND);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ErrorResponse> handleOutOfStockException(OutOfStockException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        return buildErrorResponse(ex, HttpStatus.PAYMENT_REQUIRED);
    }

<<<<<<< Updated upstream
=======
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
        return buildErrorResponse(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestStateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestStateException(InvalidRequestStateException ex) {
        return buildErrorResponse(ex, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        Throwable cause = ex.getCause();

        return switch (cause) {
            case ResourceNotFoundException e -> handleResourceNotFoundException(e);
            case PasswordMismatchException e -> handlePasswordMismatchException(e);
            case ResourceAlreadyFoundException e -> handleResourceAlreadyFoundException(e);
            case OutOfStockException e -> handleOutOfStockException(e);
            case InsufficientBalanceException e -> handleInsufficientBalanceException(e);
            case UnauthorizedException e -> handleUnauthorizedException(e);
            case BadRequestException e -> handleBadRequestException(e);
            case InvalidRequestStateException e -> handleInvalidRequestStateException(e);
            case null, default -> buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }

>>>>>>> Stashed changes
}

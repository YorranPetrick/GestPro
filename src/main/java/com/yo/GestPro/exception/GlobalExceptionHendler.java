package com.yo.GestPro.exception;

import com.yo.GestPro.models.error.ErrorField;
import com.yo.GestPro.models.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHendler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorField> errorFields = e.getFieldErrors()
                .stream()
                .map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage()))
                .toList();

        return ErrorResponse.standardError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation Error",
                errorFields);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(Exception e) {
        List<ErrorField> errorFields = List.of(
                new ErrorField(e.getMessage(),
                        e.getCause() != null ?
                                e.getCause().getMessage() :"No additional details"));

        return ErrorResponse.standardError(
                HttpStatus.BAD_REQUEST.value(),
                "An error occurred",
                errorFields
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleBadCredentialsException(BadCredentialsException e){
        List<ErrorField> errorFields = List.of(
                new ErrorField(e.getMessage(),
                        e.getCause() != null ?
                                e.getCause().getMessage() : "No additional details"));

        return ErrorResponse.standardError(
                HttpStatus.UNAUTHORIZED.value(),
                "Invalid credentials",
                errorFields);
    }
}

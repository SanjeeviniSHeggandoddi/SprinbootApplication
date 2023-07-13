package com.assignment.springbootapplication.exception.advice;

import com.assignment.springbootapplication.payload.response.ErrorResponse;
import com.assignment.springbootapplication.payload.response.Violations;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse  handleInvalidArgument(MethodArgumentNotValidException methodArgumentNotValidException){
        ErrorResponse error = new ErrorResponse();
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            error.getViolations().add(new Violations(fieldError.getDefaultMessage()));
        }
        return error;
    }
}

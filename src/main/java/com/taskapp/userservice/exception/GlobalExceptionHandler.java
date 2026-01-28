package com.taskapp.userservice.exception;

import com.taskapp.userservice.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GenericRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleGenericRuntimeException(GenericRuntimeException ex) {

        return new ApiResponse(false, ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleValidationException(MethodArgumentNotValidException ex) {

        String errorMessage =
                ex.getBindingResult()
                        .getAllErrors().get(0)
                        .getDefaultMessage();

        return new ApiResponse(false, errorMessage);
    }
}

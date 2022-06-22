package com.example.employeecompany.controller;

import com.example.employeecompany.dto.ErrorResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponseDTO handleError(ResponseStatusException ex) {

        return ErrorResponseDTO.builder()
                .status(ex.getStatus())
                .message(ex.getReason())
                .build();
    }
}

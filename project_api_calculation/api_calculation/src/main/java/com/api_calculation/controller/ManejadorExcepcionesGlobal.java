package com.api_calculation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for handling validation exceptions and customizing the response.
 */
@RestControllerAdvice
public class ManejadorExcepcionesGlobal {

    /**
     * Handles validation exceptions and customizes the error response.
     *
     * @param ex The exception that was thrown.
     * @return A {@link ResponseEntity} containing the error message map and HTTP status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarExcepcionesDeValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            errores.put(nombreCampo, mensajeError);
        });

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
package com.sgp.sgp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/*
    Manejador global de excepciones.
    Convierte las excepciones personalizadas en
    respuestas HTTP con un mensaje legible.
*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
        Recurso no encontrado -> HTTP 404
    */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> manejarNoEncontrado(
            RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("mensaje", ex.getMessage()));
    }

    /*
        Recurso duplicado -> HTTP 409 (Conflict)
    */
    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<Map<String, String>> manejarDuplicado(
            RecursoDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("mensaje", ex.getMessage()));
    }
}
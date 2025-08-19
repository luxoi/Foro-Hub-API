package com.forohub.forohub.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Throwable rootCause = ex.getRootCause();
        if (rootCause != null && rootCause.getMessage().toLowerCase().contains("duplicate")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: El título ya existe, debe ser único.");
        } else if (rootCause != null && rootCause.getMessage().toLowerCase().contains("foreign key")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: El ID proporcionado no existe.");
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error de base de datos.");
    }



}

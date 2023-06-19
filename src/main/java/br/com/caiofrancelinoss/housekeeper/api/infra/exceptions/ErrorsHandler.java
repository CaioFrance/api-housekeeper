package br.com.caiofrancelinoss.housekeeper.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundHandler() {
        return ResponseEntity.notFound().build();
    }
}

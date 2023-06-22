package br.com.caiofrancelinoss.housekeeper.api.app.dto;

import org.springframework.validation.FieldError;

public record ErrorsDataValidation(String field, String message) {
    public ErrorsDataValidation(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}

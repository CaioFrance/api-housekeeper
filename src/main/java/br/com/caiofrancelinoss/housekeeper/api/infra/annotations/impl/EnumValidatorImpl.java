package br.com.caiofrancelinoss.housekeeper.api.infra.annotations.impl;

import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.ProfileStatus;
import br.com.caiofrancelinoss.housekeeper.api.infra.annotations.EnumValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, Object> {
    private List<?> allowedValues;
    
    private List<?> allValues;
    
    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        setAllValues(constraintAnnotation.enumClass().getEnumConstants());
        setAllowedValues(constraintAnnotation.acceptedValues());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        allowedValues = allowedValues.stream().map(v -> ProfileStatus.valueOf(v.toString())).toList();
            
        if (!new HashSet<>(allValues).containsAll(allowedValues)) {
            return false;
        }
        
        return allowedValues.contains(value);
    }
    
    private <T> void setAllowedValues(T[] allowedValues) {
        this.allowedValues = Arrays.stream(allowedValues).toList();
    }
    
    private <T> void setAllValues(T[] allValues) {
        this.allValues = Arrays.stream(allValues).toList();
    }
}

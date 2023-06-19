package br.com.caiofrancelinoss.housekeeper.api.infra.annotations;

import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.ProfileStatus;
import br.com.caiofrancelinoss.housekeeper.api.infra.annotations.impl.EnumValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = EnumValidatorImpl.class)
@Target({ FIELD })
@Retention(RUNTIME)
@NotNull
@ReportAsSingleViolation
public @interface EnumValidator {
    String message() default "Value is not valid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    
    Class<? extends Enum<?>> enumClass();
    
    String[] acceptedValues();
}

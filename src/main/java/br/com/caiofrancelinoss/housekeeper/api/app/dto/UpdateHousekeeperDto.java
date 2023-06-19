package br.com.caiofrancelinoss.housekeeper.api.app.dto;

import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.ProfileStatus;
import br.com.caiofrancelinoss.housekeeper.api.infra.annotations.EnumValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UpdateHousekeeperDto(
    @NotBlank
    String cellphone,
    String telephone,
    @NotBlank
    @Email
    String email,
    String experience,
    @NotNull
    @EnumValidator(enumClass = ProfileStatus.class, acceptedValues = {"AVAILABLE", "BUSY"})
    ProfileStatus status,
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 3, fraction = 2)
    BigDecimal pricePerHour,
    @NotNull
    @Valid
    AddressDataDto address
) {}

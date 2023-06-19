package br.com.caiofrancelinoss.housekeeper.api.app.dto;

import java.math.BigDecimal;

import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.Gender;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.ProfileStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record CreateHousekeeperDto(
    @NotBlank
    String name,
    @NotBlank
    String cellphone,
    String telephone,
    @NotBlank
    @Email
    String email,
    @Min(value = 18)
    int age,
    String experience,
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 3, fraction = 2)
    BigDecimal pricePerHour,
    @NotNull
    @Valid
    AddressDataDto address,
    @NotNull
    Gender gender
) {}

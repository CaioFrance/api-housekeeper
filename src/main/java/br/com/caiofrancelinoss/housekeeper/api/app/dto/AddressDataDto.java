package br.com.caiofrancelinoss.housekeeper.api.app.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressDataDto(
    @NotBlank
    String street,
    @NotBlank
    String neighborhood,
    @NotBlank
    String zipCode,
    @NotBlank
    String city,
    @NotBlank
    String state,
    String complement,
    @NotBlank
    String number
) {}

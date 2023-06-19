package br.com.caiofrancelinoss.housekeeper.api.app.dto;

import br.com.caiofrancelinoss.housekeeper.api.domain.models.Address;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.Housekeeper;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.Gender;
import br.com.caiofrancelinoss.housekeeper.api.domain.models.enums.ProfileStatus;

import java.math.BigDecimal;

public record HousekeeperDetailDto(
    Long id,
    String name,
    String cellphone,
    String telephone,
    String email,
    int age,
    String experience,
    BigDecimal pricePerHour,
    ProfileStatus status,
    Gender gender,
    Address address
) {
    public HousekeeperDetailDto(Housekeeper housekeeper) {
        this(housekeeper.getId(), housekeeper.getName(), housekeeper.getCellphone(), housekeeper.getTelephone(),
            housekeeper.getEmail(), housekeeper.getAge(), housekeeper.getExperience(), housekeeper.getPricePerHour(),
            housekeeper.getStatus(), housekeeper.getGender(), housekeeper.getAddress());
    } 
}

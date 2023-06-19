package br.com.caiofrancelinoss.housekeeper.api.domain.models;

import br.com.caiofrancelinoss.housekeeper.api.app.dto.AddressDataDto;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Address {
    private String street;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String state;
    private String complement;
    private String number;

    public Address(AddressDataDto addressDataDto) {
        this.street = addressDataDto.street();
        this.neighborhood = addressDataDto.neighborhood();
        this.zipCode = addressDataDto.zipCode();
        this.city = addressDataDto.city();
        this.state = addressDataDto.state();
        this.complement = addressDataDto.complement();
        this.number = addressDataDto.number();
    }

    public void dataUpdate(AddressDataDto address) {
        if (address.state() != null && !address.state().equals("")) {
            this.state = address.state();
        }
        
        if (address.street() != null && !address.street().equals("")) {
            this.street = address.street();
        }
        
        if (address.zipCode() != null && !address.zipCode().equals("")) {
            this.zipCode = address.zipCode();
        }
        
        if (address.city() != null && !address.city().equals("")) {
            this.city = address.city();
        }
        
        if (address.complement() != null) {
            this.complement = address.complement();
        }
        
        if (address.neighborhood() != null && !address.neighborhood().equals("")) {
            this.neighborhood = address.neighborhood();
        }
        
        if (address.number() != null && !address.number().equals("")) {
            this.number = address.number();
        }
    }
}

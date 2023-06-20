package br.com.caiofrancelinoss.housekeeper.api.domain.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProfileStatus {
    AVAILABLE("AVAILABLE"),
    BUSY("BUSY"),
    UNAVAILABLE("UNAVAILABLE"),
    EXCLUDED("EXCLUDED");
    
    @JsonValue
    private final String value;
    
    ProfileStatus(String value) {
        this.value = value;
    }
    
    @JsonCreator
    public static ProfileStatus fromString(String value) {
        for (ProfileStatus ps : ProfileStatus.values()) {
            if (ps.value.equals(value)) {
                return ps;
            }
        }
        return null;
    }
}
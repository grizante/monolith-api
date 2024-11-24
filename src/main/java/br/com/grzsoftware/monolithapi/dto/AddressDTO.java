package br.com.grzsoftware.monolithapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class AddressDTO {

    @Schema(description = "Street where client lives", example = "Rua xxx, Bairro x, Número 1")
    @NotNull
    private String street;

    @Schema(description = "City where client lives", example = "São Paulo")
    @NotNull
    private String city;

    @Schema(description = "State where client lives", example = "São Paulo")
    @NotNull
    private String state;

    public AddressDTO() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

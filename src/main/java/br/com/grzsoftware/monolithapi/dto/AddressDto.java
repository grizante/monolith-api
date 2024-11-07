package br.com.grzsoftware.monolithapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class AddressDto {
    @Schema(description = "Street where client lives", example = "Rua xxx, Bairro x, Número 1")
    private String street;

    @Schema(description = "City where client lives", example = "São Paulo")
    private String city;

    @Schema(description = "State where client lives", example = "São Paulo")
    private String state;

    public AddressDto() {
    }

    public AddressDto(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
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

package br.com.grzsoftware.monolithapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateClientResponseDto extends CreateClientDTO {
    @Schema(description = "Client id", example = "0")
    private Long id;

    public CreateClientResponseDto(Long id, String name, String email, String phone, String cpf, String rg, AddressDto address) {
        super(name, email, phone, cpf, rg, address);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

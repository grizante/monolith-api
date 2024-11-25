package br.com.grzsoftware.monolithapi.clients.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateClientResponseDto extends CreateClientDTO {
    @Schema(description = "Client id", example = "0")
    private Long id;

    public CreateClientResponseDto(Long id, String name, String email, String phone, String cpf, String rg, AddressDTO address) {
        super(name, email, phone, cpf, rg, address);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

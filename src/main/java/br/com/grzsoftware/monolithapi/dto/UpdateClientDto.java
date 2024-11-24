package br.com.grzsoftware.monolithapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateClientDto extends CreateClientDTO {
    @Schema(description = "Client id", example = "0")
    private Long id;

    public UpdateClientDto(String name, String email, String phone, String cpf, String rg, AddressDTO address) {
        super(name, email, phone, cpf, rg, address);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package br.com.grzsoftware.monolithapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class CreateClientDTO {
    @NotNull
    @Size(min = 2, max = 50)
    @Schema(description = "Client name", example = "James")
    private String name;

    @NotNull
    @Email
    @Schema(description = "Client email", example = "james@example.com")
    private String email;

    @NotNull
    @Size(min = 9, max = 50)
    @Schema(description = "Client phone number", example = "99999999999")
    private String phone;

    @NotNull
    @CPF
    @Schema(description = "Client CPF", example = "11111111111")
    private String cpf;

    @NotNull
    @Schema(description = "Client RG", example = "11111111")
    private String rg;

    @Schema(description = "Client address")
    private AddressDTO address;

    public CreateClientDTO() {}

    public CreateClientDTO(String name, String email, String phone, String cpf, String rg, AddressDTO address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.rg = rg;
        this.address = address;
    }

    public CreateClientDTO(String name, String email, String phone, String cpf, String rg) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.rg = rg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public AddressDTO getAddress() {
        return address;
    }
}

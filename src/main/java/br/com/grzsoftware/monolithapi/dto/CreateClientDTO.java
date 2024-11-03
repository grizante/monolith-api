package br.com.grzsoftware.monolithapi.dto;

import br.com.grzsoftware.monolithapi.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateClientDTO {
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 9, max = 50)
    private String phone;

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    private String rg;

    private Address address;

    public CreateClientDTO() {}

    public CreateClientDTO(String name, String email, String phone, String cpf, String rg, Address address) {
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

    public void setAddress(Address address) {
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

    public Address getAddress() {
        return address;
    }
}

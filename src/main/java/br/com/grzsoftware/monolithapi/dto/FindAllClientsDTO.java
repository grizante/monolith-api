package br.com.grzsoftware.monolithapi.dto;

public class FindAllClientsDTO extends PaginationQueryDTO {
    public FindAllClientsDTO() {}
    public FindAllClientsDTO(int page, int size, String sort) {
        super(page, size, sort);
    }
}

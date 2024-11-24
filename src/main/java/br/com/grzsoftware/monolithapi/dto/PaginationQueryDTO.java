package br.com.grzsoftware.monolithapi.dto;

public class PaginationQueryDTO {
    private int page = 0;
    private int size = 10;
    private String sort;

    public PaginationQueryDTO() {
    }

    public PaginationQueryDTO(int page, int size, String sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSort() {
        return sort;
    }
}

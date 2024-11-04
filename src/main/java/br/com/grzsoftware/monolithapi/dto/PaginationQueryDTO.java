package br.com.grzsoftware.monolithapi.dto;

import org.springframework.data.domain.Sort;

public class PaginationQueryDTO {
    private int page = 0;
    private int size = 10;
    private String sort;

    public PaginationQueryDTO() {}

    public PaginationQueryDTO(int page, int size, String sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}

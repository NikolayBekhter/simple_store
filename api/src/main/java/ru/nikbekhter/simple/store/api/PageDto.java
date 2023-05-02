package ru.nikbekhter.simple.store.api;

import java.util.List;

public class PageDto<E> {
    private List<E> items;
    private int page;
    private int totalPages;

    public PageDto() {
    }

    public PageDto(List<E> items, int page, int totalPages) {
        this.items = items;
        this.page = page;
        this.totalPages = totalPages;
    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

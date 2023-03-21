package ru.nikbekhter.simple.store.core.api;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class PageDto<E> {
    private List<E> items;
    private int page;
    private int totalPages;

}

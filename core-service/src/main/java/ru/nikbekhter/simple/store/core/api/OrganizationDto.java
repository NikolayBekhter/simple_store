package ru.nikbekhter.simple.store.core.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nikbekhter.simple.store.core.entities.Product;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
    private Long id;
    private String title;
    private String description;
    private List<Product> products;
    private String owner;
}

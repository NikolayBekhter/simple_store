package ru.nikbekhter.simple.store.core.api;

import lombok.Builder;
import lombok.Data;
import ru.nikbekhter.simple.store.core.entities.Discount;
import ru.nikbekhter.simple.store.core.entities.Review;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String organizationTitle;
    private BigDecimal price;
    private int quantity;
    private boolean isConfirmed;
    private Discount discount;
    private Review review;
}

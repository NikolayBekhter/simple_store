package ru.nikbekhter.simple.store.core.api;

import lombok.Data;
import ru.nikbekhter.simple.store.core.entities.Product;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiscountDto {
    private Long id;
    private List<Product> products;
    private int dis;
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;
}

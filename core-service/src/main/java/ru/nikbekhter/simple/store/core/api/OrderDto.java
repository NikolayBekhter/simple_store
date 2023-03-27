package ru.nikbekhter.simple.store.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;
    private String createdAt;
    private boolean status;
}

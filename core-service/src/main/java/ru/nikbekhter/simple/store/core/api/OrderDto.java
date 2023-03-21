package ru.nikbekhter.simple.store.core.api;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;
    private String createdAt;

    public OrderDto() {
    }

    public OrderDto(Long id, String username, List<OrderItemDto> items, BigDecimal totalPrice, String createdAt) {
        this.id = id;
        this.username = username;
        this.items = items;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", items=" + items +
                ", totalCost=" + totalPrice +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}

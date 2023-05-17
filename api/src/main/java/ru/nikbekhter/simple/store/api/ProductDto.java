package ru.nikbekhter.simple.store.api;

import java.math.BigDecimal;

public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String organizationTitle;
    private BigDecimal price;
    private int quantity;
    private boolean isConfirmed;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, String description, String organizationTitle, BigDecimal price, int quantity, boolean isConfirmed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.organizationTitle = organizationTitle;
        this.price = price;
        this.quantity = quantity;
        this.isConfirmed = isConfirmed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizationTitle() {
        return organizationTitle;
    }

    public void setOrganizationTitle(String organizationTitle) {
        this.organizationTitle = organizationTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}

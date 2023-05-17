package ru.nikbekhter.simple.store.api;

public class PurchaseHistoryDto {
    private Long id;
    private String email;
    private String productTitle;
    private String organization;
    private int quantity;
    private String datePurchase;

    public PurchaseHistoryDto() {
    }

    public PurchaseHistoryDto(Long id, String email, String productTitle, String organization, int quantity, String datePurchase) {
        this.id = id;
        this.email = email;
        this.productTitle = productTitle;
        this.organization = organization;
        this.quantity = quantity;
        this.datePurchase = datePurchase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(String datePurchase) {
        this.datePurchase = datePurchase;
    }
}

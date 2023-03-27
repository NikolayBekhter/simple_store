package ru.nikbekhter.simple.store.core.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "organization")
    private Organization organization;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "is_confirmed")
    private boolean isConfirmed;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_id")
    private Discount discount;
    //отзывы и оценки вынесены в класс Review
    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;

    /*
    - Ключевых слов;
    - Таблицы характеристик;
    */


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", organization=" + organization +
                ", price=" + price +
                ", quantity=" + quantity +
                ", isConfirmed=" + isConfirmed +
                ", discount=" + discount +
                ", review=" + review +
                '}';
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}

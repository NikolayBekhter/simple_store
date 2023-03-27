package ru.nikbekhter.simple.store.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase_history")
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "product_title")
    private String productTitle;
    @Column(name = "organization")
    private String organization;
    @Column(name = "quantity")
    private int quantity;
    @CreationTimestamp
    @Column(name = "date_purchase", nullable = false, updatable = false)
    private LocalDateTime datePurchase;
}

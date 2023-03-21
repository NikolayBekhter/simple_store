package ru.nikbekhter.simple.store.core.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "discount")
    private List<Product> products;
    @Column(name = "dis")
    private int dis;//уточнить правильный тип данных
    @Column(name = "start_date", nullable = false, updatable = false)
    private LocalDateTime startDate;
    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;
}

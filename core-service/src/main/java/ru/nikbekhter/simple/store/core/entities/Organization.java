package ru.nikbekhter.simple.store.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "logo_id")
    private Logo logo;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.PERSIST)
    private List<Product> products;
    //для записи владельца организации
    @Column(name = "owner")
    private String owner;

    @Column(name = "is_active")
    private boolean isActive;
}

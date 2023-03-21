package ru.nikbekhter.simple.store.core.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "review_text")
    private String reviewText;

    @Column(name = "mark")
    private int mark;
}

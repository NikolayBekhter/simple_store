package ru.nikbekhter.simple.store.auth.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
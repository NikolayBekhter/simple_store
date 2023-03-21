package ru.nikbekhter.simple.store.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikbekhter.simple.store.core.entities.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}

package ru.nikbekhter.simple.store.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikbekhter.simple.store.core.entities.PurchaseHistory;

import java.util.List;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
    List<PurchaseHistory> findAllByEmailIgnoreCase(String email);
}

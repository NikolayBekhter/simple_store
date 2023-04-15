package ru.nikbekhter.simple.store.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikbekhter.simple.store.auth.entities.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

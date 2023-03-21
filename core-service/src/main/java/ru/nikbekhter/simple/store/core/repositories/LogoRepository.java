package ru.nikbekhter.simple.store.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikbekhter.simple.store.core.entities.Logo;

@Repository
public interface LogoRepository extends JpaRepository<Logo, Long> {
}

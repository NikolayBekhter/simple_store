package ru.nikbekhter.simple.store.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikbekhter.simple.store.core.entities.Organization;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByTitleIgnoreCase(String title);
}

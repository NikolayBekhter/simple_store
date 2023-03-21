package ru.nikbekhter.simple.store.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nikbekhter.simple.store.auth.api.ResourceNotFoundException;
import ru.nikbekhter.simple.store.auth.entities.Role;
import ru.nikbekhter.simple.store.auth.repositories.RoleRepository;

@Component
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(() -> new ResourceNotFoundException("Роль с id: " + name + " не найдена!"));
    }

}

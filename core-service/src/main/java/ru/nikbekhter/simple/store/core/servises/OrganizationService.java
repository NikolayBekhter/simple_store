package ru.nikbekhter.simple.store.core.servises;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.core.api.OrganizationDto;
import ru.nikbekhter.simple.store.core.api.ResourceNotFoundException;
import ru.nikbekhter.simple.store.core.converters.OrganizationConverter;
import ru.nikbekhter.simple.store.core.entities.Logo;
import ru.nikbekhter.simple.store.core.entities.Organization;
import ru.nikbekhter.simple.store.core.repositories.OrganizationRepository;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository repository;
    private final LogoService logoService;
    private final OrganizationConverter organizationConverter;

    public void save(OrganizationDto organizationDto, String username/*, MultipartFile file*/) throws IOException {
        Organization organization = organizationConverter.dtoToEntity(organizationDto);
        organization.setOwner(username);
//        organization.setLogo(logoService.save(file));
        log.info("Добавлена новая организация {}", organization);
        repository.save(organization);
    }

    public Organization findByTitleIgnoreCase(String title) {
        return repository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new ResourceNotFoundException("Организация с названием: " + title + " не найдена."));
    }

    public Logo findLogoByTitleOrganization(String title) {
        return findByTitleIgnoreCase(title).getLogo();
    }
}

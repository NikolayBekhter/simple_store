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
import ru.nikbekhter.simple.store.core.utils.MyQueue;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository repository;
    private final LogoService logoService;
    private final OrganizationConverter organizationConverter;
    private MyQueue<Organization> myQueue = new MyQueue<>();

    public void save(OrganizationDto organizationDto, String username/*, MultipartFile file*/) throws IOException {
        Organization organization = organizationConverter.dtoToEntity(organizationDto);
        organization.setOwner(username);
//        organization.setLogo(logoService.save(file));
        log.info("Добавлена новая организация {}", organization);
        myQueue.enqueue(organization);
        repository.save(organization);
    }

    public Organization notConfirmed() throws ResourceNotFoundException {
        if (myQueue.isEmpty()) {
            List<Organization> notActiveList = repository.findAllByIsActive(false);
            if (notActiveList.isEmpty()) {
                throw new ResourceNotFoundException("Не подтвержденных организаций больше нет.");
            }
            for (Organization organization : notActiveList) {
                myQueue.enqueue(organization);
            }
        }
        return myQueue.dequeue();
    }

    public Organization findByTitleIgnoreCase(String title) throws ResourceNotFoundException {
        Organization organization = repository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new ResourceNotFoundException("Организация с названием: " + title + " не найдена."));
        if (organization.isActive()) {
            return organization;
        } else {
            return null;
        }
    }

    public Organization findByTitleForQueue(String title) throws ResourceNotFoundException {
        return repository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new ResourceNotFoundException("Организация с названием: " + title + " не найдена."));
    }

    public Logo findLogoByTitleOrganization(String title) {
        return findByTitleIgnoreCase(title).getLogo();
    }

    public void confirm(String title) {
        Organization organization = repository.findByTitleIgnoreCase(title).get();
        organization.setActive(true);
        repository.save(organization);
    }
}

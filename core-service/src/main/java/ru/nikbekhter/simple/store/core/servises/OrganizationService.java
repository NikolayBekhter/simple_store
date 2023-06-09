package ru.nikbekhter.simple.store.core.servises;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nikbekhter.simple.store.api.OrganizationDto;
import ru.nikbekhter.simple.store.api.ResourceNotFoundException;
import ru.nikbekhter.simple.store.core.converters.OrganizationConverter;
import ru.nikbekhter.simple.store.core.entities.Logo;
import ru.nikbekhter.simple.store.core.entities.Organization;
import ru.nikbekhter.simple.store.core.repositories.OrganizationRepository;
import ru.nikbekhter.simple.store.core.utils.MyQueue;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository repository;
    private final LogoService logoService;
    private final OrganizationConverter organizationConverter;
    private MyQueue<Organization> myQueue = new MyQueue<>();

    public void save(OrganizationDto organizationDto, String username, MultipartFile file) throws IOException {
        Organization organization = organizationConverter.dtoToEntity(organizationDto);
        organization.setOwner(username);
        organization.setLogo(logoService.save(file));
        log.info("Добавлена новая организация {}, её собственник {}", organization.getTitle(), organization.getOwner());
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
            throw new ResourceNotFoundException("Организация с названием: " + title + " не подтверждена.");
        }
    }

    public Organization findByTitle(String title) throws ResourceNotFoundException {
        return repository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new ResourceNotFoundException("Организация с названием: " + title + " не найдена."));
    }

    public Logo findLogoByTitleOrganization(String title) {
        return findByTitle(title).getLogo();
    }

    public void confirm(String title) {
        Organization organization = repository.findByTitleIgnoreCase(title).get();
        organization.setActive(true);
        repository.save(organization);
    }

    public List<OrganizationDto> findAll() {
        return repository.findAll()
                .stream()
                .map(organizationConverter::entityToDto)
                .collect(Collectors.toList());
    }

    public void orgBun(Long id) {
        Organization organization = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Организация с id: " + id + " не найдена!"));
        organization.setActive(!organization.isActive());
        repository.save(organization);
    }

    public boolean isOrgBun(String title) {
        Organization organization = repository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new ResourceNotFoundException("Организация с названием: " + title + " не найдена."));
        return organization.isActive();
    }
}

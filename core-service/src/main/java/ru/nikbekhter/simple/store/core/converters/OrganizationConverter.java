package ru.nikbekhter.simple.store.core.converters;

import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.api.OrganizationDto;
import ru.nikbekhter.simple.store.core.entities.Organization;

@Service
public class OrganizationConverter {
    public Organization dtoToEntity(OrganizationDto dto) {
        return Organization.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    public OrganizationDto entityToDto(Organization organization) {
        OrganizationDto dto = new OrganizationDto();
        dto.setId(organization.getId());
        dto.setTitle(organization.getTitle());
        dto.setDescription(organization.getDescription());
        dto.setOwner(organization.getOwner());
        dto.setActive(organization.isActive());
        return dto;
    }
}

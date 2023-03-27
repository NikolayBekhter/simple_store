package ru.nikbekhter.simple.store.core.converters;

import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.core.api.OrganizationDto;
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
        return OrganizationDto.builder()
                .id(organization.getId())
                .title(organization.getTitle())
                .description(organization.getDescription())
                .owner(organization.getOwner())
                .products(organization.getProducts())
                .build();
    }
}

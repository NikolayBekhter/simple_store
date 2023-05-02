package ru.nikbekhter.simple.store.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.nikbekhter.simple.store.api.OrganizationDto;
import ru.nikbekhter.simple.store.api.ResourceNotFoundException;
import ru.nikbekhter.simple.store.core.converters.OrganizationConverter;
import ru.nikbekhter.simple.store.core.servises.OrganizationService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/org")
public class OrganizationController {
    private final OrganizationService organizationService;
    private final OrganizationConverter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCompany(@RequestParam("owner") String companyOwner,
                              @RequestParam("name") String companyName,
                              @RequestParam("description") String companyDescription,
                              @RequestParam(value = "companyImage", required = false) MultipartFile companyImage) throws IOException {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setTitle(companyName);
        organizationDto.setDescription(companyDescription);
        organizationService.save(organizationDto, companyOwner, companyImage);
    }

    @GetMapping("/{title}")
    public OrganizationDto getByTitle(@PathVariable String title) {
        return converter.entityToDto(organizationService.findByTitleIgnoreCase(title));
    }

    @GetMapping("/not_confirmed")
    public OrganizationDto notConfirmed() throws ResourceNotFoundException {
        return converter.entityToDto(organizationService.notConfirmed());
    }

    @GetMapping("/confirm/{title}")
    public void confirm(@PathVariable String title) {
        organizationService.confirm(title);
    }

    @GetMapping
    public List<OrganizationDto> findAll() {
        return organizationService.findAll();
    }

    @GetMapping("/bun/{id}")
    public void orgBun(@PathVariable Long id) {
        organizationService.orgBun(id);
    }
}

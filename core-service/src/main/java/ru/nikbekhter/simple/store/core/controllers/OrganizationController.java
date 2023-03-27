package ru.nikbekhter.simple.store.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nikbekhter.simple.store.core.api.OrganizationDto;
import ru.nikbekhter.simple.store.core.api.ResourceNotFoundException;
import ru.nikbekhter.simple.store.core.converters.OrganizationConverter;
import ru.nikbekhter.simple.store.core.servises.OrganizationService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/org")
public class OrganizationController {
    private final OrganizationService organizationService;
    private final OrganizationConverter converter;

    @PostMapping
    public void save(/*@RequestParam("file")MultipartFile file,*/
                     @RequestBody OrganizationDto organizationDto,
                     @RequestHeader(name = "username") String username) throws IOException {
        organizationService.save(organizationDto, username/*, file*/);
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
}

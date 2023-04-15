package ru.nikbekhter.simple.store.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikbekhter.simple.store.core.entities.Logo;
import ru.nikbekhter.simple.store.core.servises.OrganizationService;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/logo")
public class LogoController {
    private final OrganizationService organizationService;

    @GetMapping("/{title}")
    public ResponseEntity<?> getLogoByTitleOrganization(@PathVariable String title) {
        System.out.println(title);
        Logo logo = organizationService.findLogoByTitleOrganization(title);
        return /*new ResponseEntity<>(logo, HttpStatus.OK);*/
                ResponseEntity.ok()
                .header("fileName", logo.getOriginalFileName())
                .contentType(MediaType.valueOf(logo.getContentType()))
                .contentLength(logo.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(logo.getBytes())));
    }
}

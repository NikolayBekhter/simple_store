package ru.nikbekhter.simple.store.auth.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nikbekhter.simple.store.api.NotificationDto;
import ru.nikbekhter.simple.store.auth.services.NotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDto> findAllByUsername(@RequestHeader String username) {
        return notificationService.findAllByUsername(username);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        notificationService.delete(id);
    }
}

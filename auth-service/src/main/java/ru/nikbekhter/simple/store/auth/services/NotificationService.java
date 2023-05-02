package ru.nikbekhter.simple.store.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.api.NotificationDto;
import ru.nikbekhter.simple.store.auth.converters.NotificationConverter;
import ru.nikbekhter.simple.store.auth.entities.Notification;
import ru.nikbekhter.simple.store.auth.repositories.NotificationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;
    private final NotificationConverter converter;

    public void save(NotificationDto notificationDto) {
        Notification notification = converter.dtoToEntity(notificationDto);
        repository.save(notification);
    }

    public List<NotificationDto> findAllByUsername(String username) {
        return repository.findAllBySendTo(username)
                .stream()
                .map(converter::entityToDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

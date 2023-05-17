package ru.nikbekhter.simple.store.auth.converters;

import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.api.NotificationDto;
import ru.nikbekhter.simple.store.auth.entities.Notification;

import java.time.format.*;

@Service
public class NotificationConverter {
    public NotificationDto entityToDto(Notification notification) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setTitle(notification.getTitle());
        notificationDto.setCreatedAt(notification.getCreatedAt().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        notificationDto.setContent(notification.getContent());
        notificationDto.setSendTo(notification.getSendTo());
        return notificationDto;
    }

    public Notification dtoToEntity(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setTitle(notificationDto.getTitle());
        notification.setContent(notificationDto.getContent());
        notification.setSendTo(notificationDto.getSendTo());
        return notification;
    }
}

package ru.nikbekhter.simple.store.auth.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.*;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.api.NotificationDto;
import ru.nikbekhter.simple.store.auth.services.NotificationService;

@Service
@Log4j2
@RequiredArgsConstructor
public class MyMailSender {
    private final NotificationService notificationService;
    private final SimpleMailMessage mailMessage = new SimpleMailMessage();
    private final MailSender mailSender;

    public void sendMailNotification(NotificationDto notificationDto) {
        mailMessage.setFrom("nik.noreply.b@mail.ru");
        mailMessage.setTo(notificationDto.getSendTo());
        mailMessage.setSubject(notificationDto.getTitle());
        mailMessage.setText(notificationDto.getContent());
        mailSender.send(mailMessage);
        notificationService.save(notificationDto);
        log.info("Отправлено уведомление: {}, получатель: {}", notificationDto.getContent(), notificationDto.getSendTo());
    }
}

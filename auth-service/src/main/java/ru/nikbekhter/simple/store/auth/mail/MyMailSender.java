package ru.nikbekhter.simple.store.auth.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.auth.api.MessageDto;

@Service
@Log4j2
@RequiredArgsConstructor
public class MyMailSender {

    private final SimpleMailMessage mailMessage = new SimpleMailMessage();
    private final MailSender mailSender;

    public void sendMailMessage(MessageDto messageDto) {
        mailMessage.setFrom("nik.noreply.b@mail.ru");
        mailMessage.setTo(messageDto.getRecipient());
        mailMessage.setSubject("Сообщение от администратора.");
        mailMessage.setText(messageDto.getMessage());
        mailSender.send(mailMessage);
        log.info("Отправлено сообщение: {}, получатель: {}", messageDto.getMessage(), messageDto.getRecipient());
    }
}

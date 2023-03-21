package ru.nikbekhter.simple.store.auth.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MyMailSender {

    private final SimpleMailMessage mailMessage = new SimpleMailMessage();
    private final MailSender mailSender;

    public void regConfirm(String email) {

    }
}

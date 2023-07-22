package com.example.demo.utili;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SendMailForgot {

    @Autowired
    MailSender mailSender;

    public void sendMail(String text, String to, String sub) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(text);
        message.setTo(to);
        message.setSubject(sub);
        mailSender.send(message);
    }
}

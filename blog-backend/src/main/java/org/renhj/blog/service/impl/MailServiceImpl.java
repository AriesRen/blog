package org.renhj.blog.service.impl;

import org.renhj.blog.pojo.dto.Mail;
import org.renhj.blog.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    @Async
    public void sendTextMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getMessage());
        message.setTo(mail.getTo());
        message.setFrom(mail.getFrom());
        mailSender.send(message);
        System.out.println("发送成功");
    }

    @Override
    @Async
    public void sendHtmlMail(Mail mail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getMessage());
            helper.setTo(mail.getTo());
            helper.setFrom(mail.getFrom());
            mail.getAttachment().forEach((attachment) -> {
                try {
                    helper.addAttachment(attachment, new File(attachment));
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

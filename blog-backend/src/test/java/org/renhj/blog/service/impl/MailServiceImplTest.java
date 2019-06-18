package org.renhj.blog.service.impl;

import org.junit.Test;
import org.renhj.blog.BlogApplicationTests;
import org.renhj.blog.pojo.dto.Mail;
import org.renhj.blog.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.Assert.*;

public class MailServiceImplTest extends BlogApplicationTests {

    @Autowired
    private MailService mailService;

    @Test
    public void sendTextMail() throws IOException {
        Mail mail = new Mail();
        mail.setFrom("无名万物 <18435175817@163.com>");
        mail.setTo("1761179051@qq.com");
        mail.setMessage("测试邮件阿斯顿发大水发射点发射点发的说法都发阿道夫阿道夫阿道夫");
        mail.setSubject("测试邮件");
        mailService.sendTextMail(mail);
        System.in.read();
    }
}
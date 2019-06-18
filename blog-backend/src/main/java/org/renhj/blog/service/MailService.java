package org.renhj.blog.service;

import org.renhj.blog.pojo.dto.Mail;

public interface MailService {

    void sendTextMail(Mail mail);

    void sendHtmlMail(Mail mail);

}

package com.heo.service.impl;

import com.heo.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Service
public class EmailServiceImpl implements IEmailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    TemplateEngine templateEngine;

    @Override
    public void sendRegistEmail(String id, String to) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setSubject("激活邮件");

        Context context = new Context();
        context.setVariable("id", id);
        String content = templateEngine.process("email-regist",context);

        helper.setText(content, true);
        helper.setFrom("justinniu@yeah.net");

        javaMailSender.send(message);
    }
}

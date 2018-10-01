package com.heo;

import com.heo.service.IEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import javax.annotation.Resource;
import javax.mail.MessagingException;


/**
 * @Auth justinniu
 * @Date 2018/10/1
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    private JavaMailSender javaMailSender;

    @Resource
    TemplateEngine templateEngine;


    @Autowired
    private IEmailService emailService;

    private String username;

    @Test
    public void testSendSimple() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("justinniu@yeah.net");
        message.setTo("834355795@qq.com");
        message.setSubject("标题：测试标题");
        message.setText("测试内容部份");
        javaMailSender.send(message);
    }

    @Test
    public void testTemplateMail() throws MessagingException {
        emailService.sendRegistEmail("123","979292329@qq.com");

    }
}

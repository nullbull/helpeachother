package com.heo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Auth justinniu
 * @Date 2018/10/1
 * @Desc
 */
@Component
public class EmailService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    JavaMailSender mailSender;

    public void sendHtmlEmail(String deliver, String receiver, String carbonCopy, String subject, String content, boolean isHtml) {
        long startTimestamp = System.currentTimeMillis();
        logger.info("Start send email ...");

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1129114837@qq.com");
            message.setTo("979292329@qq.com");
            message.setSubject("标题：测试标题");
            message.setText("测试内容部份");
            mailSender.send(message);

            logger.info("Send email success, cost {} million seconds", System.currentTimeMillis() - startTimestamp);
        } catch (Exception e) {
            logger.error("Send email failed, error message is {} \n", e.getMessage());
            e.printStackTrace();
        }
    }

}



package com.heo.service.impl;

import com.heo.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    TemplateEngine templateEngine;

    @Override
    public boolean sendRegistEmail(String id, String to) {
        logger.info("发送激活邮件 ...");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject("激活邮件");

            Context context = new Context();
            context.setVariable("id", id);
            String content = templateEngine.process("email-regist",context);

            helper.setText(content, true);
            helper.setFrom("justinniu@yeah.net");

            javaMailSender.send(message);
            logger.info("发送到：{}",to);
            logger.info("发送成功！");
            return true;
        } catch (MessagingException e) {
            logger.error("发送失败，错误信息： {} \n", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    public void sendHtmlEmail(String deliver, String receiver, String carbonCopy, String subject, String content, boolean isHtml) {
        long startTimestamp = System.currentTimeMillis();
        logger.info("Start send email ...");

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1129114837@qq.com");
            message.setTo("979292329@qq.com");
            message.setSubject("标题：测试标题");
            message.setText("测试内容部份");
            javaMailSender.send(message);
            logger.info("Send email success, cost {} million seconds", System.currentTimeMillis() - startTimestamp);
        } catch (Exception e) {
            logger.error("Send email failed, error message is {} \n", e.getMessage());
            e.printStackTrace();
        }
    }
}

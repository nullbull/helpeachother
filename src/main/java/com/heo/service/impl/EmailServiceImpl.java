package com.heo.service.impl;

import com.alibaba.fastjson.JSON;
import com.heo.entity.dto.ExpressOrderEmailDTO;
import com.heo.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
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

    @Override
    public boolean sendFinshOrderEmail(String content, String sendTo) {
        String methodDesc = "发送完成通知邮件";
        try {
            logger.info(methodDesc + "开始 >>>>>>>>>>> + content : {} >>>>>>>>>>> sendTo : {}", content, sendTo);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(sendTo);
            helper.setSubject("");
            ExpressOrderEmailDTO emailDTO = JSON.parseObject(content, ExpressOrderEmailDTO.class);
            Context context = new Context();
            context.setVariable("providerName", emailDTO.getProviderName());
            context.setVariable("expressOrderId", emailDTO.getExpressOrderId());
            context.setVariable("price", emailDTO.getPrice());
            context.setVariable("finishTime", emailDTO.getFinishTime());
            String html = templateEngine.process("email-finishOrder",context);

            helper.setText(html, true);
            helper.setFrom("justinniu@yeah.net");
            javaMailSender.send(message);
            logger.info(methodDesc + "完成");
            return true;
        } catch (Exception e) {
            logger.error("");
            return false;
        }
    }
}

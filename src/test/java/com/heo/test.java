package com.heo;

import com.heo.common.utils.RedisLock;
import com.heo.service.IEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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


    @Autowired
    RedisLock redisLock;
    @Resource
    TemplateEngine templateEngine;


    @Autowired
    private IEmailService emailService;

    private String username;


    @Autowired
    private JedisPool jedisPool;
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
    @Test
    public void testJedis() {
        Jedis jedis = jedisPool.getResource();
        jedis.set("zwt", "mylove");
        System.out.println(jedis.get("zwt"));
    }


    @Test
    public void testRedisLock() {
        Jedis jedis = jedisPool.getResource();
        String key = "lock:test";
        String v1 = "1";
        String v2 = "2";
        ExecutorService service = Executors.newFixedThreadPool(2);
        Thread c = new Thread("A") {
            @Override
            public void run() {
                try {
                    if (redisLock.lock(jedis, key, v2)){
                        System.out.println(Thread.currentThread().getName() + "------获得锁");
                        redisLock.releaseLock(jedis, key, v2);
                    }
                    System.out.println(Thread.currentThread().getName() + "------未得锁");
                } catch (Exception e) {

                }
            }
        };

        Thread b = new Thread("B") {
            @Override
            public void run() {
                try {
                    if (redisLock.lock(jedis, key, v2)){
                        System.out.println(Thread.currentThread().getName() + "------获得锁");
                        redisLock.releaseLock(jedis, key, v2);
                    }
                    System.out.println(Thread.currentThread().getName() + "------未得锁");
                } catch (Exception e) {

                }


            }
        };
       c.start();
       b.start();




    }
}


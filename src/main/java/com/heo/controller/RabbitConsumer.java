package com.heo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auth justinniu
 * @Date 2018/11/3
 * @Desc
 */
@Component
public class RabbitConsumer {
    @RabbitListener(queues = "create.a")
    public void receive(String s) {
        System.out.println("messgage: " + s );
    }
}

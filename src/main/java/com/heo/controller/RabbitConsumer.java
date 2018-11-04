package com.heo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * @Auth justinniu
 * @Date 2018/11/3
 * @Desc
 */
@Component
public class RabbitListener {
    //    @Value("rabbit.queue.createA")
    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "create.a")
    public void receive(String s) {
        System.out.println("messgage: " + s );
    }
}

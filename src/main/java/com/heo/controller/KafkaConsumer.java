package com.heo.controller;

import com.heo.common.utils.RedisUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Auth justinniu
 * @Date 2018/9/30
 * @Desc
 */
@Component
public class KafkaConsumer {
    @Autowired
    RedisUtil redisUtil;
    @KafkaListener(topics = "test")
    public void listen (ConsumerRecord<?, ?> record) throws Exception {
        System.out.println(redisUtil.get(record.value().toString()));
        System.out.printf("topic = %s, offset = %s, value = %s \n", record.topic(), record.key(), record.value());
    }
}

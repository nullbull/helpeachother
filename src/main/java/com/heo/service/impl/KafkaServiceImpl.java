package com.heo.service.impl;

import com.heo.common.Serializer.PropertiesUtil;
import com.heo.common.constant.Constants;
import com.heo.common.utils.RedisUtil;
import com.heo.service.IKafkaService;
import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auth justinniu
 * @Date 2018/9/30
 * @Desc
 */
@Service
public class KafkaServiceImpl implements IKafkaService {


    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private RedisUtil redisUtil;
    private class ProducerThread implements Runnable {
        private int i = 0;
        private Producer<String, String> producer;
        private CountDownLatch countDownLatch;
        private String key;
        private String value;
        ProducerThread(Producer<String, String> producer, String key, String value, CountDownLatch countDownLatch) {
            this.producer = producer;
            this.countDownLatch = countDownLatch;
            this.key = key;
            this.value = value;
        }
        @Override
        public void run() {
            producer.send(new ProducerRecord<String, String>("test", key, value), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (metadata != null) {
                        try {
                            Thread.sleep(1000);
                            countDownLatch.countDown();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        System.out.println(i+"  发送成功："+"checksum: "+metadata.checksum()+" offset: "+metadata.offset()+" partition: "+metadata.partition()+" topic: "+metadata.topic());
                    }
                    if (e != null) {
                        System.out.println(i+" 异常："+e.getMessage());
                    }

                }

            });
        }
    }

    /**
     *
     * @param id expressOrder Id
     * @param value 传递的message
     * @return
     */
    @Override
    public boolean sendMessage(Long id, String value) {
        try {
            Producer<String, String> producer = new KafkaProducer<String, String>(PropertiesUtil.getProperties());
            ExecutorService service = Executors.newSingleThreadScheduledExecutor();
            Thread thread = new Thread(() ->{
                service.shutdown();
                producer.close();
                logger.info("producer>>>>>>>>>>>>close");
            });
            /**
             * 这个key是 redis的key， 消费者获取key 去redis里获取数据
             */
            String key = Constants.REDIS_KEY + id;
            redisUtil.set(key, value);
            CountDownLatch cd = new CountDownLatch(1);

            service.execute(new ProducerThread(producer, Constants.KAFKA_KEY, key, cd));
            cd.await();
            thread.start();
            return true;
        }catch (Exception e) {
            logger.error("发送kafka失败， e", e);
        }
        return false;
    }
}

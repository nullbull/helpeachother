//package com.heo.app.config;
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
///**
// * @Auth justinniu
// * @Date 2018/11/3
// * @Desc
// */
//@Component
//public class RabbitMqConfig {
////   @Value("rabbitmq.queue.nameA")
//   private String CreateA = "create.a";
//
////   @Value("rabbitmq.queue.nameB")
//   private String CreateB = "create.b";
//
////   @Value("rabbitmq.exchange")
//   private String DefaultExchange = "zwt";
//    @Bean(name = "create.a")
//    public Queue createA() {
//        return new Queue(CreateA);
//    }
//
//    @Bean(name = "create.c")
//    public Queue createB() {
//        return new Queue(CreateB);
//    }
//
//
//    @Bean
//    public TopicExchange exchange() {
//        return new TopicExchange(DefaultExchange);
//    }
//}

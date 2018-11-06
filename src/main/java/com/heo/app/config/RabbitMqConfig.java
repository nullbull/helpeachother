package com.heo.app.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @Auth justinniu
 * @Date 2018/11/3
 * @Desc
 */
@Component
public class RabbitMqConfig {
    private String CreateA = "create.a";

    private String CreateB = "create.b";

    private String DefaultExchange = "zwt";

    @Value("${rabbit-mq.emailExchange}")
    private String EAMIL_EXCHANGE;

    @Value("${rabbit-mq.emailQueue}")
    private String EMAIL_QUEUE;
    @Bean(name = "create.a")
    public Queue createA() {
        return new Queue(CreateA);
    }

    @Bean(name = "create.b")
    public Queue createB() {
        return new Queue(CreateB);
    }


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(DefaultExchange);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(createA()).to(exchange()).with("create.#");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(createB()).to(exchange()).with("create.#");
    }


    @Bean(name = "email")
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE);
    }

    @Bean
    public DirectExchange exchangeDirect() {
        return new DirectExchange(EAMIL_EXCHANGE);
    }
    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(emailQueue()).to(exchangeDirect()).with(EMAIL_QUEUE);
    }

}

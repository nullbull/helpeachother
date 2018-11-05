package com.heo.app.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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

}

package com.heo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.heo.dao")
public class HelpeachotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpeachotherApplication.class, args);
    }
}

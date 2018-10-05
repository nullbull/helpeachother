package com.heo.controller;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auth justinniu
 * @Date 2018/10/5
 * @Desc
 */
@Component
@EnableScheduling
public class QuartzTest {
    @Scheduled(cron="0/5 * * * * ?")
    public void job() {
        System.out.println("每五秒执行一次");
    }

}

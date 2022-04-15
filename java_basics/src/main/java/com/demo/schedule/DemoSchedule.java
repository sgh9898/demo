package com.demo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务
 *
 * @author Song gh on 2022/4/15.
 */
@Component
public class DemoSchedule {

    /** 定时任务 (every 10s) */
    @Scheduled(fixedDelayString = "PT1M")
    public void simpleTask() {
        System.out.println("简单的定时任务 " + new Date());
    }
}

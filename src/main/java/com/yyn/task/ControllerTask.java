package com.yyn.task;
/*
   
   @author yyn
   @version 1.8
   @create 2019-12-03-15:33
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ControllerTask {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }
    public void run(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        };
        threadPoolTaskScheduler.schedule(runnable,new CronTrigger("0/5 * * * * *"));
    }
    public void shutdown(){
        threadPoolTaskScheduler.shutdown();
    }
}

package com.example.javaspring2022.jobs;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Scheduled(fixedRate = 1000)
    public void bruh(){
       System.out.println("scheluded task");
    }
}

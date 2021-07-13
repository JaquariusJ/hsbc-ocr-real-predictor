package com._4paradim.hsbc.ocr.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfig {

    @Bean("threadPoolExecutor")
    public ExecutorService threadPoolExecutor(){
        return Executors.newFixedThreadPool(10);
    }
}

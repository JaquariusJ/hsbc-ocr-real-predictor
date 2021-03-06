package com._4paradim.hsbc.ocr.server.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableAsync
public class ThreadPoolConfig {



    @Bean("threadPoolExecutor")
    public ExecutorService threadPoolExecutor(){
        ThreadFactory threadPoolFactory = new ThreadFactoryBuilder().setNameFormat("async-thread").build();
        //使用tomcat中的线程池，基于jdk里的线程池做了优化
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                10,16,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                threadPoolFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
        //使用包装后的线程池，后续为线程池与主线程通信做准备
        return TtlExecutors.getTtlExecutorService(threadPool);
    }
}

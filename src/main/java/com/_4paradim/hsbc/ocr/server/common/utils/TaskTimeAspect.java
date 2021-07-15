package com._4paradim.hsbc.ocr.server.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@Slf4j
@Aspect
public class TaskTimeAspect {

    @Pointcut("@annotation(com._4paradim.hsbc.ocr.server.common.annotation.TaskTime)")
    public void method(){

    }

    @Before("method()")
    public void before(ProceedingJoinPoint joinPoint){

    }

    @After("method()")
    public void after(){
        System.out.println("after");
    }

    @Around("method()")
    public Object computeTime(ProceedingJoinPoint proceedingJoinPoint){
        Object proceed = null;
        try {
            LocalDateTime stime = LocalDateTime.now();
            proceed = proceedingJoinPoint.proceed();
            LocalDateTime etime = LocalDateTime.now();
            long timemillis = Duration.between(etime, stime).toMillis();
            log.info(Thread.currentThread().getName()+" 任务执行时间为 "+timemillis+" 毫秒");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }
}

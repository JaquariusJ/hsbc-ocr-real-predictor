package com._4paradim.hsbc.ocr.server.common.utils;

import com._4paradim.hsbc.ocr.server.common.annotation.TaskTime;
import com._4paradim.hsbc.ocr.server.common.enums.TimeType;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@Aspect
public class TaskTimeAspect {


    private static final TransmittableThreadLocal<Map<TimeType, Long>> threadLocal = new TransmittableThreadLocal<>();

    @Pointcut("@annotation(com._4paradim.hsbc.ocr.server.common.annotation.TaskTime)")
    public void method() {

    }


    @Around("method()")
    public Object computeTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object proceed = null;
        LocalDateTime stime = LocalDateTime.now();
        proceed = proceedingJoinPoint.proceed();
        LocalDateTime etime = LocalDateTime.now();
        long timemillis = Duration.between(stime, etime).toMillis();
        //打印方法运行时间
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = signature.getMethod().getDeclaringClass().getName();
        String methodName = signature.getMethod().getName();
        log.info("invoke method [ " + className + "." + methodName + " ] :" + timemillis);
        //将结果放到threadlocal中的map
        TaskTime annotation = signature.getMethod().getAnnotation(TaskTime.class);
        TimeType type = annotation.type();
        Map<TimeType, Long> minMap = threadLocal.get();
        if (minMap == null) {
            minMap = new HashMap<>();
        }
        minMap.put(type, timemillis);
        threadLocal.set(minMap);
        return proceed;
    }


    public static Map<TimeType, Long> getTimeMap() {
        return threadLocal.get();
    }



}

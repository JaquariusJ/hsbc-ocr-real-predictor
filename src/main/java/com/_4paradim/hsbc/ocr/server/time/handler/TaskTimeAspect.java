package com._4paradim.hsbc.ocr.server.time.handler;

import com._4paradim.hsbc.ocr.server.time.annotation.TaskTime;
import com._4paradim.hsbc.ocr.server.time.enums.TimeType;
import com._4paradim.hsbc.ocr.server.time.interfaces.CallBack;
import com._4paradim.hsbc.ocr.server.time.vo.MethodRunTime;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@Aspect
public class TaskTimeAspect {


    private static final TransmittableThreadLocal<List<MethodRunTime>> threadLocal = new TransmittableThreadLocal<>();

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Pointcut("@annotation(com._4paradim.hsbc.ocr.server.time.annotation.TaskTime)")
    public void method() {

    }

    @After("method()")
    public void after(){

    }

    @Around("method()")
    public Object computeTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LocalDateTime stime = LocalDateTime.now();
        //业务方法
        Object proceed = proceedingJoinPoint.proceed();
        LocalDateTime etime = LocalDateTime.now();
        //计算时间
        long timemillis = Duration.between(stime, etime).toMillis();
        //将结果放到threadlocal中
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        setThreadLocalData(timemillis, signature);
        //调用回调方法
        Object[] args = proceedingJoinPoint.getArgs();
        runCallBack(signature,args);
        return proceed;
    }

    /**
     * 调用回调函数
     * @param signature
     * @param args
     */
    private void runCallBack(MethodSignature signature,Object[] args) {
        TaskTime annotation = signature.getMethod().getAnnotation(TaskTime.class);
        Class backendClass = annotation.callback();
        if(!CallBack.class.isAssignableFrom(backendClass)){
            String error_info = "Tasktime backend Type must [CallBack]";
            log.error(error_info);
            throw new RuntimeException(error_info);
        }
        boolean isInterface = backendClass.isInterface();
        if(!isInterface){
            log.info("call callback function: ["+backendClass+".callback()]");

            //从spring容器中获取
            CallBack callback = (CallBack) beanFactory.getBean(backendClass);
            if(callback == null){
                String error_info = backendClass + "need into the spring container";
                log.info(error_info);
                throw new RuntimeException(error_info);
            }
            //将源方法和参数传递进去
            callback.callback(signature.getMethod(),args,threadLocal.get());
            //清理
            threadLocal.remove();
        }
    }

    /**
     * 讲线程所有的时间数据放到threalocal中
     * @param timemillis
     * @param signature
     */
    private void setThreadLocalData(long timemillis, MethodSignature signature) {
        String className = signature.getMethod().getDeclaringClass().getName();
        String methodName = signature.getMethod().getName();
        log.info("invoke method [ " + className + "." + methodName + " ] run :" + timemillis +" ms");
        TaskTime annotation = signature.getMethod().getAnnotation(TaskTime.class);
        TimeType type = annotation.type();
        String desc = annotation.desc();
        type.setDesc(desc);
        List<MethodRunTime> timeList = threadLocal.get();
        if (timeList == null) {
            timeList = new ArrayList<>();
        }
        MethodRunTime methodRunTime = new MethodRunTime();
        methodRunTime.setMethodName(methodName);
        methodRunTime.setClassName(className);
        methodRunTime.setTimeType(type);
        methodRunTime.setRuntime(timemillis);
        timeList.add(methodRunTime);
        threadLocal.set(timeList);
    }


}

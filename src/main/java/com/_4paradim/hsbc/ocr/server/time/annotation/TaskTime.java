package com._4paradim.hsbc.ocr.server.time.annotation;

import com._4paradim.hsbc.ocr.server.time.enums.TimeType;
import com._4paradim.hsbc.ocr.server.time.interfaces.CallBack;

import java.lang.annotation.*;


/**
 * 该注解用于计算方法运行时间
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskTime {

    TimeType type() default TimeType.OTHER_TIME;

    String desc() default "";

    Class callback() default CallBack.class;

}

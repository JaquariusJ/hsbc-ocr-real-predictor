package com._4paradim.hsbc.ocr.server.common.annotation;

import java.lang.annotation.*;


/**
 * 该注解用于计算方法运行时间
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskTime {

    String name() default "";

}

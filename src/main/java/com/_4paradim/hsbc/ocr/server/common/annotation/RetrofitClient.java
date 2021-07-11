package com._4paradim.hsbc.ocr.server.common.annotation;


import com._4paradim.hsbc.ocr.server.common.http.RetrofitConfig;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RetrofitClient {

    String value() default "";

    Class config() default RetrofitConfig.class;
}

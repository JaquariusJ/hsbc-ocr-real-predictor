package com._4paradim.hsbc.ocr.server.common.annotation;


import com._4paradim.hsbc.ocr.server.common.http.RetrofitConfig;

import java.lang.annotation.*;


/**
 * 标注该注解的service会根据RetrofitConfig里的配置自动被构建成实例，并注入到spring容器中
 * 该注解声明一个service为retrofit的client，用于请求第三方服务
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RetrofitClient {

    String value() default "";

    Class config() default RetrofitConfig.class;
}

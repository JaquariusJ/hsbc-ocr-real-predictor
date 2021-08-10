package com._4paradim.hsbc.ocr.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@MapperScan("com._4paradim.hsbc.ocr.server.*.mapper")
@SpringBootApplication
public class HsbcOcrRealPredictorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsbcOcrRealPredictorApplication.class, args);
    }

}

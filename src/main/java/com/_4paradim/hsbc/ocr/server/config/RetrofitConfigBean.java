package com._4paradim.hsbc.ocr.server.config;

import com._4paradim.hsbc.ocr.server.common.http.RetrofitCluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetrofitConfigBean {

    @Bean(initMethod = "init")
    public RetrofitCluster retrofitCluster(){
        return new RetrofitCluster();
    }
}

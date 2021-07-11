package com._4paradim.hsbc.ocr.server.api.config;

import com._4paradim.hsbc.ocr.server.common.http.RetrofitConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Data
@ConfigurationProperties(prefix = "socr")
public class SocrConfig extends RetrofitConfig{

    private String baseUrl;
    private Integer connectTimeout = 10000;
    private Integer readTimeout = 10000;
    private Integer writeTimeout = 10000;
    private TimeUnit timeoutTimeUnit = TimeUnit.MILLISECONDS;
    private Integer maxIdleConnections = 5;
    private Long keepAliveDuration = 5L;
    private TimeUnit keepAliveDurationTimeUnit = TimeUnit.MINUTES;
    private Boolean retryOnConnectionFailure = false;
    private Integer connectErrorThreshold = 20;

}

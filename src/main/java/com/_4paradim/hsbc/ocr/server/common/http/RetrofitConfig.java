package com._4paradim.hsbc.ocr.server.common.http;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuanht on 2019/11/24
 */
@Data
@Component
public class RetrofitConfig {

    private String baseUrl;
    public Integer connectTimeout = 10000;
    public Integer readTimeout = 10000;
    public Integer writeTimeout = 10000;
    public TimeUnit timeoutTimeUnit = TimeUnit.MILLISECONDS;
    public Integer maxIdleConnections = 5;
    public Long keepAliveDuration = 5L;
    public TimeUnit keepAliveDurationTimeUnit = TimeUnit.MINUTES;
    public Boolean retryOnConnectionFailure = false;
    public Integer connectErrorThreshold = 20;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RetrofitConfig that = (RetrofitConfig) object;
        return
                Objects.equals(connectTimeout, that.connectTimeout) &&
                Objects.equals(readTimeout, that.readTimeout) &&
                Objects.equals(writeTimeout, that.writeTimeout) &&
                timeoutTimeUnit == that.timeoutTimeUnit &&
                Objects.equals(maxIdleConnections, that.maxIdleConnections) &&
                Objects.equals(keepAliveDuration, that.keepAliveDuration) &&
                keepAliveDurationTimeUnit == that.keepAliveDurationTimeUnit &&
                Objects.equals(retryOnConnectionFailure, that.retryOnConnectionFailure) &&
                Objects.equals(connectErrorThreshold, that.connectErrorThreshold);
    }
}

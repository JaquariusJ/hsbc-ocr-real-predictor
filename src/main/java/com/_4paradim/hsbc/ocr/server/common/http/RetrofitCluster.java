package com._4paradim.hsbc.ocr.server.common.http;

import com._4paradim.hsbc.ocr.server.common.annotation.RetrofitClient;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.Assert;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Set;

@Slf4j
public class RetrofitCluster implements BeanFactoryAware {

    private static String PACKGE = "com._4paradim.hsbc.ocr.server";

    @Autowired
    private AutowireCapableBeanFactory beanFactory;


    private BeanFactory mybeanFactory;

    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

    public void init(){
        //扫描改包下的RetrofitClient注解，将被注解类全部创建，并注入到spring容器中
        Reflections f = new Reflections(PACKGE);
        Set<Class<?>> svcs = f.getTypesAnnotatedWith(RetrofitClient.class);
        svcs.forEach(svcClass -> {
            //获取该svc的RetrofitClient注解
            RetrofitClient annotation = svcClass.getAnnotation(RetrofitClient.class);
            Class configClass = annotation.config();
            System.out.println(configClass);
            RetrofitConfig retrofitConfig = (RetrofitConfig) beanFactory.getBean(configClass);
            //获取配置类，对配置项进行检查
            checkConfig(retrofitConfig);
            //创建service
            Object svc = createService(svcClass, retrofitConfig);
            //注册service到spring容器中
            String beanName = svcClass.getSimpleName();
            defaultListableBeanFactory.registerSingleton(beanName,svc);
            beanFactory.autowireBean(svc);
            log.info("register svc: "+beanName+" to spring context " );
        });
    }

    public Object createService(Class svcClazz,RetrofitConfig retrofitConfig){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(retrofitConfig.getMaxIdleConnections(),retrofitConfig.getKeepAliveDuration(),retrofitConfig.getKeepAliveDurationTimeUnit()))
                .readTimeout(retrofitConfig.getReadTimeout(),retrofitConfig.getTimeoutTimeUnit())
                .connectTimeout(retrofitConfig.getConnectTimeout(),retrofitConfig.getTimeoutTimeUnit())
                .writeTimeout(retrofitConfig.getWriteTimeout(),retrofitConfig.getTimeoutTimeUnit())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitConfig.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
//
        Object service = retrofit.create(svcClazz);
        log.info("create service :"+svcClazz.getName());
        return service;
    }

    protected void checkConfig(RetrofitConfig config) {
        Assert.notNull(config, "retrofit config is null");
        Assert.notNull(config.getBaseUrl(),"baseUrl is null");
        Assert.notNull(config.getConnectTimeout(), "connectTimeout is null");
        Assert.notNull(config.getReadTimeout(), "readTimeout is null");
        Assert.notNull(config.getWriteTimeout(), "writeTimeout is null");
        Assert.notNull(config.getReadTimeout(), "readTimeout is null");
        Assert.notNull(config.getTimeoutTimeUnit(), "timeoutTimeUnit is null");
        Assert.notNull(config.getMaxIdleConnections(), "maxIdleConnections is null");
        Assert.notNull(config.getKeepAliveDuration(), "keepAliveDuration is null");
        Assert.notNull(config.getKeepAliveDurationTimeUnit(), "keepAliveDurationTimeUnit is null");
        Assert.notNull(config.getRetryOnConnectionFailure(), "retryOnConnectionFailure is null");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.mybeanFactory = beanFactory;
    }
}

package com._4paradim.hsbc.ocr.server.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FiledMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName("createUser","system",metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("lastModifiedTime", LocalTime.now(),metaObject);
        this.setFieldValByName("lastModifiedUser","system",metaObject);
    }
}

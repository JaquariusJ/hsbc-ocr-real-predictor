package com._4paradim.hsbc.ocr.server.scene.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "scene")
@PropertySource(value = "scene.properties",encoding = "utf-8")
public class KeyConfig {


//    private Map<String,String> business = new HashMap<>();
//
//    private Map<String,String> idcard = new HashMap<>();
//
//    private Map<String,String> vat = new HashMap<>();

    private Map<String,Object> key = new HashMap<>();




}

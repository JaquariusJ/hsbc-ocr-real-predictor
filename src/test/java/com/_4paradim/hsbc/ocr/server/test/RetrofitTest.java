package com._4paradim.hsbc.ocr.server.test;


import cn.hutool.core.util.IdUtil;
import com._4paradim.hsbc.ocr.server.api.config.SocrConfig;
import com._4paradim.hsbc.ocr.server.api.service.SocrService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RetrofitTest {

    @Lazy
    @Autowired
    private SocrService socrService;

    @Autowired
    private SocrConfig socrConnectConfig;

    @Test
    public void test1(){
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.fastUUID());
        System.out.println(IdUtil.randomUUID());
    }




}

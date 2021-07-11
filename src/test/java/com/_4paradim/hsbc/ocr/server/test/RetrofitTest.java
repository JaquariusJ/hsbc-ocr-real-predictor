package com._4paradim.hsbc.ocr.server.test;


import com._4paradim.hsbc.ocr.server.api.config.SocrConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RetrofitTest {

//    @Autowired
//    private SocrService socrService;

    @Autowired
    private SocrConfig socrConnectConfig;

    @Autowired
    private Person person;

    @Test
    public void test1(){
        System.out.println(socrConnectConfig);
        System.out.println(person);
    }


}

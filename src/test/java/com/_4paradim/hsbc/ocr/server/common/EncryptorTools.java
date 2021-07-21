package com._4paradim.hsbc.ocr.server.common;

import com._4paradim.hsbc.ocr.server.config.EncryptConfig;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptorTools {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void test1() {
        String ss = "123456";
        System.out.println(ss);
        System.out.println(stringEncryptor.encrypt(ss));
    }
}


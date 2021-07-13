package com._4paradim.hsbc.ocr.server.common;

import com._4paradim.hsbc.ocr.server.config.EncryptConfig;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptorTools {

    @Test
    public void test1() {
        EncryptConfig tools = new EncryptConfig();
        StringEncryptor stringEncryptor = tools.stringEncryptor();
        String ss = "UI_system";
        System.out.println(ss);
        System.out.println(stringEncryptor.encrypt(ss));
    }
}


package com._4paradim.hsbc.ocr.server.common;

import com._4paradim.hsbc.ocr.server.config.EncryptConfig;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EncryptorTools {


    public static void main(String[] args) {
        EncryptConfig encryptConfig = new EncryptConfig();
        StringEncryptor stringEncryptor = encryptConfig.stringEncryptor();
        String ss = "root";
        System.out.println(ss);
        System.out.println(stringEncryptor.encrypt(ss));
        System.out.println(stringEncryptor.decrypt("fFUS3OuYkmjUrF8PZh28U0GNgu4DyoGYsfRjUtN5FQE3bm77p1xjPPNpu33LBqRw"));
    }
}


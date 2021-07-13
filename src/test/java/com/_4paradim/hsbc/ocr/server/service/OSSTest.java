package com._4paradim.hsbc.ocr.server.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.ServerSideEncryptionConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OSSTest {

    static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    static String accessKeyId = "LTAI4GD88M2xFbZ7og3oNVEA";
    static String accessKeySecret = "MODVyIA5zp2mkBhu7RSyWcFOwcvFra";
    static String bucketName = "osskms";
    static String kmsMasterKeyID = "a8a4f2ad-fe65-4ccd-a634-c455450363e9";

    @Test
    public void test0() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //
        ServerSideEncryptionConfiguration sseConfig = ossClient.getBucketEncryption(bucketName);
        System.out.println("get Algorithm: " + sseConfig.getApplyServerSideEncryptionByDefault().getSSEAlgorithm());
        System.out.println("get kmsid: " + sseConfig.getApplyServerSideEncryptionByDefault().getKMSMasterKeyID());
        //
        ossClient.shutdown();
    }



    @Test
    public void test2() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        File file = new File("/Users/4paradigm/Downloads/cat818.jpeg");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "maomi.jpg", file);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader("x-oss-server-side-encryption", "KMS");
        //metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        //metadata.setObjectAcl(CannedAccessControlList.Private);
        putObjectRequest.setMetadata(metadata);

        ossClient.putObject(putObjectRequest);

        ossClient.shutdown();
    }

    @Test
    public void test3() {
        OSS ossClient =   new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        Date expiration = new Date(new Date().getTime() + 2 * 3600 * 1000);
        URL url = ossClient.generatePresignedUrl(bucketName, "maomi.jpg", expiration);
        System.out.println(url);
    }

}

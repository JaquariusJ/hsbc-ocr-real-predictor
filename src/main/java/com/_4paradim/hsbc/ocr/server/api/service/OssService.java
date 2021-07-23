package com._4paradim.hsbc.ocr.server.api.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Service
@Slf4j
@ConfigurationProperties(prefix = "oss")
@Data
public class OssService {

    private String endpointWeb;

    private String endpointEcs;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    public void uploadFile(File file, String objectName) {
        OSS ossClient = null;
        try {
            if (!file.exists()) {
                log.error(file.getName() + "不存在，无法上传oss");
                return;
            }
            ossClient = new OSSClientBuilder().build(endpointEcs, accessKeyId, accessKeySecret);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file);
            //设置kms加密
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setHeader("x-oss-server-side-encryption","AES256");
            putObjectRequest.setMetadata(metadata);
            ossClient.putObject(putObjectRequest);
            log.info(file.getName() + "上传oss成功"+",bucketName: "+bucketName+",objectName: "+objectName);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public void uploadFileInputStream(InputStream fileInputStream, String objectName) {
        OSS ossClient = null;
        try {
            if (fileInputStream == null) {
                log.error("文件流不存在，无法上传oss");
                return;
            }
            ossClient = new OSSClientBuilder().build(endpointEcs, accessKeyId, accessKeySecret);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, fileInputStream);
            //设置kms加密
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setHeader("x-oss-server-side-encryption","AES256");
            putObjectRequest.setMetadata(metadata);
            ossClient.putObject(putObjectRequest);
            log.info(objectName + "上传oss成功");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public String signUrl(String objectName) {
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endpointWeb, accessKeyId, accessKeySecret);
            Date expiration = new Date(new Date().getTime() + 2 * 3600 * 1000);
            URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            return url.toString();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}

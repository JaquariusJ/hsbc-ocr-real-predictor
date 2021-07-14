package com._4paradim.hsbc.ocr.server.web.vo;

import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

@Data
public class PredictorRequestFile {


    private String name;

    private String originalFilename;

    private Long size;

    private byte[] bytes;

    private InputStream inputStream;

    private String contentType;

    private Resource resource;

}

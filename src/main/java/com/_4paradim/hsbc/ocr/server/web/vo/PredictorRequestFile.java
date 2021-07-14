package com._4paradim.hsbc.ocr.server.web.vo;

import lombok.Data;

import java.io.FileInputStream;
import java.io.InputStream;

@Data
public class PredictorRequestFile {


    private String filename;

    private Long filesize;

    private String basefile;

}

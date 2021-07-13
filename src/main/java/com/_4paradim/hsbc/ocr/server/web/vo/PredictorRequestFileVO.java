package com._4paradim.hsbc.ocr.server.web.vo;

import lombok.Data;

@Data
public class PredictorRequestFileVO {

    private String filename;

    private Long filesize;

    private String basefile;

}

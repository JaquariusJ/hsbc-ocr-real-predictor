package com._4paradim.hsbc.ocr.server.web.vo;


import lombok.Data;

@Data
public class PredictorResponseVO {

    private String resultCode;

    private String errorMsg;

    private String resultIsMatch;

    private PredictorRequestVO originalData;

    private String ocrResult;


}

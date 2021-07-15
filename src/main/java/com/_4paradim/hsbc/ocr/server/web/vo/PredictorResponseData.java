package com._4paradim.hsbc.ocr.server.web.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PredictorResponseData {


    private Map ocrResult = new HashMap();

    private String resultCode;

    private String requestId;

    private String errorMsg;

}

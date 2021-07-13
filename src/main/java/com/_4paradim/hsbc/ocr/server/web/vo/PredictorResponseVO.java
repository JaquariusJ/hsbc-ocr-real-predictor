package com._4paradim.hsbc.ocr.server.web.vo;


import lombok.Data;

import java.util.Map;

@Data
public class PredictorResponseVO {

    private String resultCode;

    private String errorMsg;

    private String resultIsMatch;

    private Map originalData;

    private Map ocrResult;


}

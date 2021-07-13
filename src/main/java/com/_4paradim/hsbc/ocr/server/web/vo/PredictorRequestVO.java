package com._4paradim.hsbc.ocr.server.web.vo;


import lombok.Data;


@Data
public class PredictorRequestVO {

    private PredictorRequestFileVO predictorRequestFileVO;

    private PredictorRequestDataVO predictorRequestDataVO;
}

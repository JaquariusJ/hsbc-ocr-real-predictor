package com._4paradim.hsbc.ocr.server.web.vo;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class PredictorRequest {

    private PredictorRequestFile fileVO;

    private PredictorRequestData predictorRequestData;

    private String requestId;

}

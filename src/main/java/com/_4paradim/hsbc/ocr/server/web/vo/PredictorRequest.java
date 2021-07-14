package com._4paradim.hsbc.ocr.server.web.vo;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class PredictorRequest {

    private MultipartFile fileVO;

    private PredictorRequestData predictorRequestData;
}

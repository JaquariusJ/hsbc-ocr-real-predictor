package com._4paradim.hsbc.ocr.server.web.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class PredictorRequestData {

    @NotBlank(message = "docType 不能为空")
    private String docType;

    @NotBlank(message = "docSubType 不能为空")
    private String docSubType;
}

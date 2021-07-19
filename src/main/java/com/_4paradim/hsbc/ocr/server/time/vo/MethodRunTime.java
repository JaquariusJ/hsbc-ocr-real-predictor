package com._4paradim.hsbc.ocr.server.time.vo;

import com._4paradim.hsbc.ocr.server.time.enums.TimeType;
import lombok.Data;

@Data
public class MethodRunTime {

    private String className;

    private String methodName;

    private TimeType timeType;

    private Long runtime;


}

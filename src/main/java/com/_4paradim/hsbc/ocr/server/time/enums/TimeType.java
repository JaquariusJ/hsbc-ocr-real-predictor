package com._4paradim.hsbc.ocr.server.time.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TimeType {

    INTERFACE_TIME("interface"),
    OCR_TIME("ocr"),
    ASYNC_TIME("async"),
    OTHER_TIME("other");

    private String name;

    @Getter
    @Setter
    private String desc;

    TimeType(String name) {
        this.name = name;
    }
}

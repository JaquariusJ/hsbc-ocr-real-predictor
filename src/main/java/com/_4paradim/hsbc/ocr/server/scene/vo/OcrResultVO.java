package com._4paradim.hsbc.ocr.server.scene.vo;

import lombok.Data;

import java.util.*;

@Data
public class OcrResultVO {

    private Map<String,Object> element = new LinkedHashMap<>();

    private List<String> boxes = new ArrayList<>();

    private List<String> texts = new ArrayList<>();

    private String origin_result;

}

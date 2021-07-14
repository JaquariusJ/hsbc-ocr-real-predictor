package com._4paradim.hsbc.ocr.server.api.vo;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SocrRequest {

    private String scene;

    private String image;

    private Map parameters = new HashMap();


}

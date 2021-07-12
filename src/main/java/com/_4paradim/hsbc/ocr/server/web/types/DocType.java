package com._4paradim.hsbc.ocr.server.web.types;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;


public enum DocType {

    IDCard("IDCard","idcard","身份证"),
    BusinessLicense("BusinessLicense","business","火车票"),
    VAT("VAT","spinvoice","专票");

    DocType(String type,String scene,String name) {
        this.type = type;
        this.scene = scene;
        this.name = name;
    }

    @Getter
    private String type;

    @Getter
    private String scene;

    @Getter
    private String name;


    public static DocType getValueByType(String docType){
        return Arrays.stream(DocType.values()).filter(n -> StringUtils.endsWithIgnoreCase(docType,n.getType())).findFirst().orElse(null);
    }


}

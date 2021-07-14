package com._4paradim.hsbc.ocr.server.web.vo;

import com._4paradim.hsbc.ocr.server.web.types.DocType;
import com.google.gson.JsonObject;
import lombok.Data;

import org.springframework.util.Assert;

import java.util.Map;


@Data
public class PredictorRequestData {

    private String docType;

    private String docSubType;

    private Map idCardFrontData;

    private Map idCardBackData;

    private Map businessLicenseData;

    private Map vatData;

    public void check(){
        Assert.isInstanceOf(DocType.class,DocType.getValueByType(docType),"[doctype] wrongful");
        if(DocType.getValueByType(docType) == DocType.IDCard){
            Assert.notNull(docSubType,"[docSubType must not be null]");
        }
    }
}

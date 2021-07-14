package com._4paradim.hsbc.ocr.server.web.vo;

import com._4paradim.hsbc.ocr.server.web.types.DocType;
import com.google.gson.JsonObject;
import lombok.Data;

import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Map;


@Data
public class PredictorRequestData {

    @NotBlank(message = "docType 不能为空")
    private String docType;

    @NotBlank(message = "docSubType 不能为空")
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

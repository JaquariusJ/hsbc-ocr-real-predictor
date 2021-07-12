package com._4paradim.hsbc.ocr.server.web.vo;


import com._4paradim.hsbc.ocr.server.web.types.DocType;
import lombok.Data;
import org.springframework.util.Assert;


@Data
public class PredictorRequestVO {

    private String docType;

    private String docSubType;

    private String idCardFrontData;

    private String idCardBackData;

    private String businessLicenseData;

    private String vatData;

    public void check(){
        Assert.isInstanceOf(DocType.class,DocType.getValueByType(docType),"[doctype] wrongful");
        if(DocType.getValueByType(docType) == DocType.IDCard){
            Assert.notNull(docSubType,"[docSubType must not be null]");
        }
    }



}

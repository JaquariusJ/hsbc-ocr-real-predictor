package com._4paradim.hsbc.ocr.server.web.service;


import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.manager.service.BusinessLicenseService;
import com._4paradim.hsbc.ocr.server.manager.service.IDCardService;
import com._4paradim.hsbc.ocr.server.manager.service.VATService;
import com._4paradim.hsbc.ocr.server.web.types.DocType;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequestVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class PredictorService {

    private static Gson gson = new GsonBuilder().create();
    @Autowired
    private IDCardService idCardService;

    @Autowired
    private BusinessLicenseService businessLicenseService;

    @Autowired
    private VATService vatService;

    public String predictor(PredictorRequestVO requestVo) throws IOException, OcrException, BusinessException {
        String docType = requestVo.getPredictorRequestDataVO().getDocType();
        DocType docTypeEnum = DocType.getValueByType(docType);
        String result = "";
        switch (docTypeEnum){
            case IDCard:
                result = idCardService.ocr(requestVo);
                break;
            case BusinessLicense:
                result = businessLicenseService.ocr(requestVo);
                break;
            case VAT:
                result = vatService.ocr(requestVo);
                break;
            default:
                break;
        }
        return result;

    }


}

package com._4paradim.hsbc.ocr.server.web.service;


import com._4paradim.hsbc.ocr.server.api.vo.SocrRequestVo;
import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.web.types.DocType;
import com._4paradim.hsbc.ocr.server.web.vo.FileVO;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequestVO;
import com.google.common.collect.Maps;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

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

    public String predictor(PredictorRequestVO requestVo, FileVO fileVO) throws IOException, OcrException, BusinessException {
        String docType = requestVo.getDocType();
        SocrRequestVo socrRequestVo = new SocrRequestVo();
        DocType docTypeEnum = DocType.getValueByType(docType);
        socrRequestVo.setImage(fileVO.getBasefile());
        socrRequestVo.setScene(docTypeEnum.getScene());
        socrRequestVo.getParameters().put("vis_flag",false);
        String result = null;
            result = "";
            switch (docTypeEnum){
                case IDCard:
                    result = idCardService.ocr(socrRequestVo);
                    break;
                case BusinessLicense:
                    result = businessLicenseService.ocr(socrRequestVo);
                    break;
                case VAT:
                    result = vatService.ocr(socrRequestVo);
                    break;
                default:
                    break;
            }

        final Map resultMap = Maps.newHashMap();;
        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement ocr_result = jsonParser.parse(result);
            JsonArray dataArray = ocr_result.getAsJsonObject().get("data").getAsJsonObject()
                    .get("result").getAsJsonArray()
                    .get(0).getAsJsonObject()
                    .get("data").getAsJsonArray();
            dataArray.forEach(n->{
                String key = n.getAsJsonObject().get("element_name").getAsString();
                String value = n.getAsJsonObject().get("element_value").getAsString();
                resultMap.put(key,value);
            });
        } catch (JsonSyntaxException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
        return gson.toJson(resultMap);

    }


}

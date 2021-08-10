package com._4paradim.hsbc.ocr.server.scene.strategy;

import com._4paradim.hsbc.ocr.server.api.service.BusinessSocrService;
import com._4paradim.hsbc.ocr.server.api.service.IDCardSocrService;
import com._4paradim.hsbc.ocr.server.api.service.VATSocrService;
import com._4paradim.hsbc.ocr.server.api.vo.SocrRequest;
import com._4paradim.hsbc.ocr.server.scene.config.KeyConfig;
import com._4paradim.hsbc.ocr.server.time.annotation.TaskTime;
import com._4paradim.hsbc.ocr.server.time.enums.TimeType;
import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com._4paradim.hsbc.ocr.server.web.types.DocType;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@Data
public class SocrStrategy implements OcrStrategy<SocrRequest,OcrResultVO> {

    @Lazy
    @Autowired
    private IDCardSocrService idCardSocrService;

    @Lazy
    @Autowired
    private BusinessSocrService businessSocrService;

    @Lazy
    @Autowired
    private VATSocrService vatSocrService;

    @Autowired
    private KeyConfig keyConfig;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @TaskTime(type = TimeType.OCR_TIME)
    public OcrResultVO ocr(SocrRequest socrRequest) throws BusinessException, OcrException, IOException {
        String scene = socrRequest.getScene();
        Call<JsonObject> reponse = null;
        switch (DocType.getValueByScene(scene)){
            case VAT:
                reponse = vatSocrService.ocr(socrRequest);
                break;
            case IDCard:
                reponse = idCardSocrService.ocr(socrRequest);
                break;
            case BusinessLicense:
                reponse =businessSocrService.ocr(socrRequest);
                break;
            default:
                break;

        }
        String url = reponse.request().url().toString();
        String method = reponse.request().method();
        log.info("method : ["+method+"], url: ["+url+"],scene: ["+socrRequest.getScene()+"],parameters: ["+socrRequest.getParameters()+"],imageSize:["+socrRequest.getImage().length()+"]");
        OcrResultVO ocrResultVO = toResult(reponse);
        Map<String, Object> newKeyMap = Maps.newHashMap();
        Map<String, Object> keyMap = keyConfig.getKey();
        DocType docType = DocType.getValueByScene(scene);
        Map<String,String> keyConfigMap= (Map<String, String>) keyMap.get(docType.getType().toLowerCase());
        keyConfigMap.forEach((new_key,old_key)->{
            boolean exists = ocrResultVO.getElement().containsKey(old_key);
            Object v = null;
            if(exists){
                v = ocrResultVO.getElement().get(old_key);
            }
            newKeyMap.put(new_key,v);
        });
        ocrResultVO.setElement(newKeyMap);
        return ocrResultVO;
    }

    public OcrResultVO toResult(Call<JsonObject> reponse) throws IOException {
        Response<JsonObject> execute = reponse.execute();
        int code = execute.code();
        if(code != 200){
            String message = execute.message();
            throw new OcrException(message);
        }
        JsonObject result = execute.body();

        final OcrResultVO ocrResultVO = new OcrResultVO();
        ocrResultVO.setOrigin_result(result.toString());
        try {
            JsonArray dataArray = result.get("data").getAsJsonObject()
                    .get("result").getAsJsonArray()
                    .get(0).getAsJsonObject()
                    .get("data").getAsJsonArray();
            dataArray.forEach(n -> {
                String key = n.getAsJsonObject().get("element_name").getAsString();
                JsonElement value_element = n.getAsJsonObject().get("element_value");
                String value = value_element.isJsonNull() ? null : value_element.getAsString();
                ocrResultVO.getElement().put(key,value);
            });

            JsonArray textsArray = result.get("data").getAsJsonObject()
                    .get("json").getAsJsonObject()
                    .get("raw_result").getAsJsonObject()
                    .get("texts").getAsJsonArray();
            textsArray.forEach( n -> {
                ocrResultVO.getTexts().add(n.getAsJsonArray().toString());
            });

            JsonArray boxesArray = result.get("data").getAsJsonObject()
                    .get("json").getAsJsonObject()
                    .get("raw_result").getAsJsonObject()
                    .get("boxes").getAsJsonArray();
            boxesArray.forEach( n -> {
                ocrResultVO.getBoxes().add(n.getAsJsonArray().toString());
            });
        } catch (JsonSyntaxException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
        return ocrResultVO;
    }


}

package com._4paradim.hsbc.ocr.server.scene.strategy;

import com._4paradim.hsbc.ocr.server.api.service.SocrService;
import com._4paradim.hsbc.ocr.server.api.vo.SocrRequest;
import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Component
public class SocrStrategy implements OcrStrategy<SocrRequest,OcrResultVO> {

    @Autowired
    private SocrService socrService;


    @Override
    public OcrResultVO ocr(SocrRequest socrRequest) throws BusinessException, OcrException, IOException {

        Call<JsonObject> reponse = socrService.ocr(socrRequest);

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

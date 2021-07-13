package com._4paradim.hsbc.ocr.server.manager.strategy;

import com._4paradim.hsbc.ocr.server.api.service.SocrService;
import com._4paradim.hsbc.ocr.server.api.vo.SocrRequestVo;
import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com.google.common.collect.Maps;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class SocrStrategy implements OcrStrategy<SocrRequestVo> {

    @Autowired
    private SocrService socrService;

    private static Gson gson = new GsonBuilder().create();

    @Override
    public String ocr(SocrRequestVo socrRequestVo) throws BusinessException, OcrException, IOException {

        Call<JsonObject> reponse = socrService.ocr(socrRequestVo);

        Response<JsonObject> execute = reponse.execute();
        int code = execute.code();
        if(code != 200){
            String message = execute.message();
            throw new OcrException(message);
        }
        JsonObject result = execute.body();

        final Map resultMap = Maps.newHashMap();
        try {
            JsonParser jsonParser = new JsonParser();
            //JsonElement ocr_result = jsonParser.parse(result);
            JsonArray dataArray = result.get("data").getAsJsonObject()
                    .get("result").getAsJsonArray()
                    .get(0).getAsJsonObject()
                    .get("data").getAsJsonArray();
            dataArray.forEach(n -> {
                String key = n.getAsJsonObject().get("element_name").getAsString();
                JsonElement value_element = n.getAsJsonObject().get("element_value");
                String value = value_element.isJsonNull() ? null : value_element.getAsString();
                resultMap.put(key, value);
            });
        } catch (JsonSyntaxException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        }
        return gson.toJson(resultMap);
    }
}

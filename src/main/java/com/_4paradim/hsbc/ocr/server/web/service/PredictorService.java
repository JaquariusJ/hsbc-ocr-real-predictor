package com._4paradim.hsbc.ocr.server.web.service;


import com._4paradim.hsbc.ocr.server.api.service.SocrService;
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
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class PredictorService {

    private static Gson gson = new GsonBuilder().create();
    @Autowired
    private SocrService socrService;

    public String predictor(String requestParam, FileVO fileVO) throws IOException, OcrException, BusinessException {
        PredictorRequestVO requestVo = gson.fromJson(requestParam, PredictorRequestVO.class);
        String docType = requestVo.getDocType();
        DocType docTypeEnum = DocType.getValueByType(docType);
        SocrRequestVo socrRequestVo = new SocrRequestVo();
        socrRequestVo.setImage(fileVO.getBasefile());
        socrRequestVo.setScene(docTypeEnum.getScene());
        socrRequestVo.getParameters().put("vis_flag",false);
        Call<String> reponse = socrService.ocr(socrRequestVo);
        Response<String> execute = reponse.execute();
        int code = execute.code();
        if(code != 200){
            String message = execute.message();
            throw new OcrException(message);
        }
        String result = execute.body();
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

package com._4paradim.hsbc.ocr.server.web.service;


import com._4paradim.hsbc.ocr.server.api.service.SocrService;
import com._4paradim.hsbc.ocr.server.api.vo.SocrRequestVo;
import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class IDCardService {

    @Autowired
    private SocrService socrService;

    public String ocr(SocrRequestVo socrRequestVo) throws IOException, OcrException {
        Call<String> reponse = socrService.ocr(socrRequestVo);
        Response<String> execute = reponse.execute();
        int code = execute.code();
        if(code != 200){
            String message = execute.message();
            throw new OcrException(message);
        }
        return execute.body();
    }

}

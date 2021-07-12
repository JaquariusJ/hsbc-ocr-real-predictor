package com._4paradim.hsbc.ocr.server.api.service;

import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * 策略的容器类
 * @param <T>
 */
public class OcrContent<T> {

    private OcrService<T> ocrService;

    public OcrContent(OcrService<T> ocrService){
        this.ocrService = ocrService;
    }

    public String ocr(T t) throws OcrException, IOException {
        Call<String> reponse = ocrService.ocr(t);
        Response<String> execute = reponse.execute();
        int code = execute.code();
        if(code != 200){
            String message = execute.message();
            throw new OcrException(message);
        }
        String result = execute.body();
        return result;
    }
}

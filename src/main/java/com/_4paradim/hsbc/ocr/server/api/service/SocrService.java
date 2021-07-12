package com._4paradim.hsbc.ocr.server.api.service;

import com._4paradim.hsbc.ocr.server.api.config.SocrConfig;
import com._4paradim.hsbc.ocr.server.api.vo.SocrRequestVo;
import com._4paradim.hsbc.ocr.server.common.annotation.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

@RetrofitClient(config = SocrConfig.class)
public interface SocrService extends OcrService<SocrRequestVo> {

    @POST("/lab/ocr/predict/ticket")
    Call<String> ocr(@Body SocrRequestVo socrRequestVo);

}

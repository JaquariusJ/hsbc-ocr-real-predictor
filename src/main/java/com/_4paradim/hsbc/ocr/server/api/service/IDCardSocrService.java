package com._4paradim.hsbc.ocr.server.api.service;

import com._4paradim.hsbc.ocr.server.api.config.IDCardRetrofitConfig;
import com._4paradim.hsbc.ocr.server.api.vo.SocrRequest;
import com._4paradim.hsbc.ocr.server.common.annotation.RetrofitClient;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

@RetrofitClient(config = IDCardRetrofitConfig.class)
public interface IDCardSocrService {

    @POST("/lab/ocr/predict/ticket")
    Call<JsonObject> ocr(@Body SocrRequest socrRequest);
}

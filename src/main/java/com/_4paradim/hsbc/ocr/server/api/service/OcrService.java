package com._4paradim.hsbc.ocr.server.api.service;

import retrofit2.Call;

/**
 * 策略接口
 * @param <T>
 */
public interface OcrService<T> {

    Call<String> ocr(T t);
}

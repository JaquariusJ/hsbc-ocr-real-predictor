package com._4paradim.hsbc.ocr.server.scene.strategy;

import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class OcrContent<T> {

    private OcrStrategy<T> ocrStrategy;

    public OcrContent(OcrStrategy<T> ocrStrategy) {
        this.ocrStrategy = ocrStrategy;
    }

    public String ocr(T t) throws OcrException, IOException, BusinessException {
        return ocrStrategy.ocr(t);
    }
}

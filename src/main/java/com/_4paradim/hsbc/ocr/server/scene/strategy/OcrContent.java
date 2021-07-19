package com._4paradim.hsbc.ocr.server.scene.strategy;

import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class OcrContent<T,R> {

    private OcrStrategy<T,R> ocrStrategy;

    public OcrContent(OcrStrategy ocrStrategy) {
        this.ocrStrategy = ocrStrategy;
    }

    public R ocr(T t) throws OcrException, IOException, BusinessException {
        return ocrStrategy.ocr(t);
    }
}

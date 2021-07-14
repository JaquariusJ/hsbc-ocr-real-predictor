package com._4paradim.hsbc.ocr.server.scene.strategy;

import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;

import java.io.IOException;

public interface OcrStrategy<T,R> {

    R ocr(T t) throws BusinessException, OcrException, IOException;
}

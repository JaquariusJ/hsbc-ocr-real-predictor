package com._4paradim.hsbc.ocr.server.config;

import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.web.types.ResponseType;
import com._4paradim.hsbc.ocr.server.web.utils.PredictorReponseResult;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OcrException.class)
    public PredictorResponse ocrException(HttpServletRequest request, Exception e){
        e.printStackTrace();
        return PredictorReponseResult.failed(ResponseType.PLATFROM_ERROR,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public PredictorResponse Exception(HttpServletRequest request, Exception e){
        e.printStackTrace();
        return PredictorReponseResult.failed(ResponseType.SERVER_ERRROR,e.getMessage());
    }
}

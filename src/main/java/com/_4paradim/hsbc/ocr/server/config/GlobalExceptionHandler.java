package com._4paradim.hsbc.ocr.server.config;

import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.web.types.ResponseType;
import com._4paradim.hsbc.ocr.server.web.utils.PredictorReponseResult;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OcrException.class)
    public PredictorResponse ocrException(HttpServletRequest request, Exception e){
        String requestId = (String) request.getAttribute("requestId");
        log.error("id: "+requestId+",error info: "+e);
        ExceptionUtils.printRootCauseStackTrace(e);
        return PredictorReponseResult.failed(requestId,ResponseType.PLATFROM_ERROR,e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public PredictorResponse IllegalArgumentException(HttpServletRequest request, Exception e){
        String requestId = (String) request.getAttribute("requestId");
        log.error("id: "+requestId+",error info: "+e);
        ExceptionUtils.printRootCauseStackTrace(e);
        return PredictorReponseResult.failed(requestId,ResponseType.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public PredictorResponse Exception(HttpServletRequest request, Exception e){
        String requestId = (String) request.getAttribute("requestId");
        log.error("id: "+requestId+",error info: "+e);
        ExceptionUtils.printRootCauseStackTrace(e);
        return PredictorReponseResult.failed(requestId,ResponseType.SERVER_ERRROR,e.getMessage());
    }
}

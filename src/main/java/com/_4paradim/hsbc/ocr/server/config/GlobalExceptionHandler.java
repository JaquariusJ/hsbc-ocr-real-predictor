package com._4paradim.hsbc.ocr.server.config;

import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.web.types.ResponseType;
import com._4paradim.hsbc.ocr.server.web.utils.PredictorReponseResult;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequestVO;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorResponseVO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Gson gson = new Gson();


    @ExceptionHandler(OcrException.class)
    public PredictorResponseVO ocrException(HttpServletRequest request,Exception e){
        String data = request.getParameter("data");
        PredictorRequestVO predictorRequestVO = gson.fromJson(data, PredictorRequestVO.class);
        e.printStackTrace();
        return PredictorReponseResult.failed(predictorRequestVO, ResponseType.PLATFROM_ERROR,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public PredictorResponseVO Exception(HttpServletRequest request,Exception e){
        String data = request.getParameter("data");
        PredictorRequestVO predictorRequestVO = gson.fromJson(data, PredictorRequestVO.class);
        e.printStackTrace();
        return PredictorReponseResult.failed(predictorRequestVO, ResponseType.SERVER_ERRROR,e.getMessage());
    }
}

package com._4paradim.hsbc.ocr.server.web.utils;

import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com._4paradim.hsbc.ocr.server.web.types.ResponseType;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorResponse;
import com.google.gson.Gson;

import java.util.Map;

public class PredictorReponseResult {

    public static PredictorResponse success(String requestId,OcrResultVO result){
        PredictorResponse responseVO = new PredictorResponse();
        responseVO.setCode(String.valueOf(ResponseType.SUCCESS.getCode()));
        responseVO.getData().setOcrResult(result.getElement());
        responseVO.getData().setRequestId(requestId);
        responseVO.getData().setErrorMsg(null);
        responseVO.getData().setResultCode(ResponseType.SUCCESS.getMessage());
        return responseVO;
    }

    public static PredictorResponse failed(String requestId,ResponseType responseType){
        PredictorResponse responseVO = new PredictorResponse();
        responseVO.setCode(String.valueOf(responseType.getCode()));
        responseVO.getData().setOcrResult(null);
        responseVO.getData().setErrorMsg(responseType.getDesc());
        responseVO.getData().setRequestId(requestId);
        responseVO.getData().setResultCode(responseType.getMessage());
        return responseVO;
    }

    public static PredictorResponse failed(String requestId,ResponseType responseType, String e){
        PredictorResponse responseVO = new PredictorResponse();
        responseVO.setCode(String.valueOf(responseType.getCode()));
        responseVO.getData().setOcrResult(null);
        responseVO.getData().setErrorMsg(e);
        responseVO.getData().setRequestId(requestId);
        responseVO.getData().setResultCode(responseType.getMessage());
        return responseVO;
    }
}

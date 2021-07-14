package com._4paradim.hsbc.ocr.server.web.utils;

import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com._4paradim.hsbc.ocr.server.web.types.ResponseType;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorResponse;
import com.google.gson.Gson;

import java.util.Map;

public class PredictorReponseResult {

    private static final Gson gson = new Gson();

    public static PredictorResponse success(String request, OcrResultVO result){
        PredictorResponse responseVO = new PredictorResponse();
        responseVO.setErrorMsg(null);
        responseVO.setResultCode(ResponseType.SUCCESS.getMessage());
        responseVO.setOcrResult(result.getElement());
        responseVO.setOriginalData(gson.fromJson(request, Map.class));
        return responseVO;
    }

    public static PredictorResponse failed(String request, ResponseType responseType){
        PredictorResponse responseVO = new PredictorResponse();
        responseVO.setErrorMsg(responseType.getDesc());
        responseVO.setResultCode(responseType.getMessage());
        responseVO.setOcrResult(null);
        responseVO.setOriginalData(gson.fromJson(request, Map.class));
        return responseVO;
    }

    public static PredictorResponse failed(String request, ResponseType responseType, String e){
        PredictorResponse responseVO = new PredictorResponse();
        responseVO.setErrorMsg(e);
        responseVO.setResultCode(responseType.getMessage());
        responseVO.setOcrResult(null);
        responseVO.setOriginalData(gson.fromJson(request, Map.class));
        return responseVO;
    }
}

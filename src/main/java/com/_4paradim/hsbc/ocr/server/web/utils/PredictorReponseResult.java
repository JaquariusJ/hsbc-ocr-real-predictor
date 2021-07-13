package com._4paradim.hsbc.ocr.server.web.utils;

import com._4paradim.hsbc.ocr.server.web.types.ResponseType;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorResponseVO;
import com.google.gson.Gson;

import java.util.Map;

public class PredictorReponseResult {

    private static final Gson gson = new Gson();

    public static PredictorResponseVO success(String request,String result){
        PredictorResponseVO responseVO = new PredictorResponseVO();
        responseVO.setErrorMsg(null);
        responseVO.setResultCode(ResponseType.SUCCESS.getMessage());
        responseVO.setOcrResult(gson.fromJson(result,Map.class));
        responseVO.setOriginalData(gson.fromJson(request, Map.class));
        return responseVO;
    }

    public static PredictorResponseVO failed(String request,ResponseType responseType){
        PredictorResponseVO responseVO = new PredictorResponseVO();
        responseVO.setErrorMsg(responseType.getDesc());
        responseVO.setResultCode(responseType.getMessage());
        responseVO.setOcrResult(null);
        responseVO.setOriginalData(gson.fromJson(request, Map.class));
        return responseVO;
    }

    public static PredictorResponseVO failed(String request,ResponseType responseType,String e){
        PredictorResponseVO responseVO = new PredictorResponseVO();
        responseVO.setErrorMsg(e);
        responseVO.setResultCode(responseType.getMessage());
        responseVO.setOcrResult(null);
        responseVO.setOriginalData(gson.fromJson(request, Map.class));
        return responseVO;
    }
}

package com._4paradim.hsbc.ocr.server.web.utils;

import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com._4paradim.hsbc.ocr.server.web.types.ResponseType;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorResponse;

public class PredictorReponseResult {

    public static PredictorResponse success(String requestId, OcrResultVO result){
        PredictorResponse responseVO = new PredictorResponse();
        responseVO.setCode(String.valueOf(ResponseType.SUCCESS.getCode()));
        responseVO.setOcrResult(result.getElement());
        responseVO.setRequestId(requestId);
        responseVO.setErrorMsg(null);
        responseVO.setResultCode(ResponseType.SUCCESS.getMessage());
        return responseVO;
    }

    public static PredictorResponse failed(String requestId,ResponseType responseType){
        PredictorResponse responseVO = new PredictorResponse();
        responseVO.setCode(String.valueOf(responseType.getCode()));
        responseVO.setOcrResult(null);
        responseVO.setErrorMsg(responseType.getDesc());
        responseVO.setRequestId(requestId);
        responseVO.setResultCode(responseType.getMessage());
        return responseVO;
    }

    public static PredictorResponse failed(String requestId,ResponseType responseType, String e){
        PredictorResponse responseVO = new PredictorResponse();
        responseVO.setCode(String.valueOf(responseType.getCode()));
        responseVO.setOcrResult(null);
        responseVO.setErrorMsg(e);
        responseVO.setRequestId(requestId);
        responseVO.setResultCode(responseType.getMessage());
        return responseVO;
    }
}

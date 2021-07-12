package com._4paradim.hsbc.ocr.server.web.utils;

import com._4paradim.hsbc.ocr.server.web.types.ResponseType;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequestVO;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorResponseVO;

public class PredictorReponseResult {

    public static PredictorResponseVO success(PredictorRequestVO request,String result){
        PredictorResponseVO responseVO = new PredictorResponseVO();
        responseVO.setErrorMsg(null);
        responseVO.setResultCode(ResponseType.SUCCESS.getMessage());
        responseVO.setOcrResult(result);
        responseVO.setOriginalData(request);
        return responseVO;
    }

    public static PredictorResponseVO failed(PredictorRequestVO request,ResponseType responseType){
        PredictorResponseVO responseVO = new PredictorResponseVO();
        responseVO.setErrorMsg(responseType.getDesc());
        responseVO.setResultCode(responseType.getMessage());
        responseVO.setOcrResult(null);
        responseVO.setOriginalData(request);
        return responseVO;
    }

    public static PredictorResponseVO failed(PredictorRequestVO request,ResponseType responseType,String e){
        PredictorResponseVO responseVO = new PredictorResponseVO();
        responseVO.setErrorMsg(e);
        responseVO.setResultCode(responseType.getMessage());
        responseVO.setOcrResult(null);
        responseVO.setOriginalData(request);
        return responseVO;
    }
}

package com._4paradim.hsbc.ocr.server.manager.service.impl;

import com._4paradim.hsbc.ocr.server.manager.vo.OcrPredictorInfo;
import com._4paradim.hsbc.ocr.server.manager.mapper.OcrPredictorInfoDao;
import com._4paradim.hsbc.ocr.server.manager.service.OcrPredictorInfoService;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequest;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequestData;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequestFile;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wujian
 * @since 2021-07-14
 */
@Service
public class OcrPredictorInfoServiceImpl extends ServiceImpl<OcrPredictorInfoDao, OcrPredictorInfo> implements OcrPredictorInfoService {

    private static final Gson gson = new GsonBuilder().create();

    @Override
    public OcrPredictorInfo request2PredictorInfo(PredictorRequest predictorRequest) {
        PredictorRequestData dataVO = predictorRequest.getPredictorRequestData();
        PredictorRequestFile fileVO = predictorRequest.getFileVO();
        OcrPredictorInfo ocrPredictorInfo = new OcrPredictorInfo();
        ocrPredictorInfo.setDocType(dataVO.getDocType());
        ocrPredictorInfo.setSubType(dataVO.getDocSubType());
        ocrPredictorInfo.setFileName(fileVO.getOriginalFilename());
        ocrPredictorInfo.setFileSize(fileVO.getSize());
        ocrPredictorInfo.setData(gson.toJson(predictorRequest.getPredictorRequestData()));
        return ocrPredictorInfo;
    }
}

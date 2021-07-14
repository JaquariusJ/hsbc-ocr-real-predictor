package com._4paradim.hsbc.ocr.server.manager.service;

import com._4paradim.hsbc.ocr.server.manager.vo.OcrPredictorInfo;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.pdfbox.filter.Predictor;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wujian
 * @since 2021-07-14
 */
public interface OcrPredictorInfoService extends IService<OcrPredictorInfo> {


    OcrPredictorInfo request2PredictorInfo(PredictorRequest predictorRequest);
}

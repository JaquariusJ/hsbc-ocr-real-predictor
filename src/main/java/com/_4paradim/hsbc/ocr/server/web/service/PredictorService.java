package com._4paradim.hsbc.ocr.server.web.service;


import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.common.utils.TaskTimeAspect;
import com._4paradim.hsbc.ocr.server.manager.service.OcrPredictorInfoService;
import com._4paradim.hsbc.ocr.server.manager.task.AsyncTask;
import com._4paradim.hsbc.ocr.server.manager.vo.OcrPredictorInfo;
import com._4paradim.hsbc.ocr.server.scene.service.Impl.BusinessLicenseService;
import com._4paradim.hsbc.ocr.server.scene.service.Impl.IDCardService;
import com._4paradim.hsbc.ocr.server.scene.service.Impl.VATService;
import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com._4paradim.hsbc.ocr.server.web.types.DocType;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class PredictorService {

    @Autowired
    private IDCardService idCardService;

    @Autowired
    private BusinessLicenseService businessLicenseService;

    @Autowired
    private VATService vatService;

    @Autowired
    private AsyncTask asyncTask;


    public OcrResultVO predictor(PredictorRequest requestVo) throws IOException, OcrException, BusinessException {
        String docType = requestVo.getPredictorRequestData().getDocType();
        DocType docTypeEnum = DocType.getValueByType(docType);
        OcrResultVO resultVo = null;

        switch (docTypeEnum){
            case IDCard:
                resultVo = idCardService.ocr(requestVo);
                break;
            case BusinessLicense:
                resultVo = businessLicenseService.ocr(requestVo);
                break;
            case VAT:
                resultVo = vatService.ocr(requestVo);
                break;
            default:
                break;
        }
        //异步发送请求，oss文档，保存数据
        asyncTask.uploadOssAndSaveData(requestVo,resultVo);
        return resultVo;

    }


}

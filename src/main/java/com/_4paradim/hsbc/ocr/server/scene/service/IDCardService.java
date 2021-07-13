package com._4paradim.hsbc.ocr.server.scene.service;


import com._4paradim.hsbc.ocr.server.api.vo.SocrRequestVo;
import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.scene.strategy.OcrContent;
import com._4paradim.hsbc.ocr.server.scene.strategy.SocrStrategy;
import com._4paradim.hsbc.ocr.server.web.types.DocType;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class IDCardService {

    @Autowired
    private SocrStrategy socrStrategy;

    public String ocr(PredictorRequestVO requestVo) throws IOException, OcrException, BusinessException {
        String docType = requestVo.getPredictorRequestDataVO().getDocType();
        DocType docTypeEnum = DocType.getValueByType(docType);
        SocrRequestVo socrRequestVo = new SocrRequestVo();
        socrRequestVo.setImage(requestVo.getPredictorRequestFileVO().getBasefile());
        socrRequestVo.setScene(docTypeEnum.getScene());
        socrRequestVo.getParameters().put("vis_flag",false);
        //使用socr进行预估
        OcrContent ocrContent = new OcrContent(socrStrategy);
        return ocrContent.ocr(socrRequestVo);
    }

}

package com._4paradim.hsbc.ocr.server.manager.service;

import com._4paradim.hsbc.ocr.server.api.vo.SocrRequestVo;
import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.manager.strategy.OcrContent;
import com._4paradim.hsbc.ocr.server.manager.strategy.SocrStrategy;
import com._4paradim.hsbc.ocr.server.web.types.DocType;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VATService {

    @Autowired
    private SocrStrategy socrStrategy;

    public String ocr(PredictorRequestVO requestVo) throws IOException, OcrException, BusinessException {
        String docType = requestVo.getDocType();
        DocType docTypeEnum = DocType.getValueByType(docType);
        SocrRequestVo socrRequestVo = new SocrRequestVo();
        socrRequestVo.setImage(requestVo.getFileVO().getBasefile());
        socrRequestVo.setScene(docTypeEnum.getScene());
        socrRequestVo.getParameters().put("vis_flag",false);
        //使用socr进行预估
        OcrContent ocrContent = new OcrContent(socrStrategy);
        return ocrContent.ocr(socrRequestVo);
    }

}
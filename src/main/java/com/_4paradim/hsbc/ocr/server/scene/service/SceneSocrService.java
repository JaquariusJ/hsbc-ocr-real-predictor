package com._4paradim.hsbc.ocr.server.scene.service;

import com._4paradim.hsbc.ocr.server.api.vo.SocrRequest;
import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.scene.strategy.OcrContent;
import com._4paradim.hsbc.ocr.server.scene.strategy.SocrStrategy;
import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com._4paradim.hsbc.ocr.server.web.types.DocType;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * socr场景预估类
 */
@Service
public class SceneSocrService implements SceneService<OcrResultVO, PredictorRequest> {

    @Autowired
    private SocrStrategy socrStrategy;

    @Override
    public OcrResultVO ocr(PredictorRequest requestVo) throws IOException, OcrException, BusinessException {
        String docType = requestVo.getPredictorRequestData().getDocType();
        String basefile = Base64.encodeBase64String(requestVo.getFileVO().getBytes());
        DocType docTypeEnum = DocType.getValueByType(docType);
        SocrRequest socrRequest = new SocrRequest();
        socrRequest.setImage(basefile);
        socrRequest.setScene(docTypeEnum.getScene());
        socrRequest.getParameters().put("vis_flag",false);
        //使用socr进行预估
        OcrContent<SocrRequest,OcrResultVO> ocrContent = new OcrContent(socrStrategy);
        return ocrContent.ocr(socrRequest);
    }

}

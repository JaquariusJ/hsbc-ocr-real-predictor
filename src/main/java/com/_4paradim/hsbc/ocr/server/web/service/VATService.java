package com._4paradim.hsbc.ocr.server.web.service;

import com._4paradim.hsbc.ocr.server.api.service.OcrContent;
import com._4paradim.hsbc.ocr.server.api.service.SocrService;
import com._4paradim.hsbc.ocr.server.api.vo.SocrRequestVo;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Service
public class VATService {

    @Autowired
    private SocrService socrService;


    public String ocr(SocrRequestVo socrRequestVo) throws IOException, OcrException {
        OcrContent ocrContent = new OcrContent(socrService);
        return ocrContent.ocr(socrRequestVo);
    }
}

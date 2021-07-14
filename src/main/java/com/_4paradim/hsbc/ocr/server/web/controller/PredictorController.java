package com._4paradim.hsbc.ocr.server.web.controller;


import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com._4paradim.hsbc.ocr.server.web.service.PredictorService;
import com._4paradim.hsbc.ocr.server.web.utils.PredictorReponseResult;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequestData;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequest;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequestFile;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/realtime")
public class PredictorController {

    @Autowired
    private PredictorService predictorService;

    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    @PostMapping("/predictor")
    public PredictorResponse predictor(@RequestParam("file") MultipartFile file, @RequestParam("data") String request, @RequestParam("appCode") String appCode) throws BusinessException, OcrException, IOException {
        PredictorRequestData dataVO = gson.fromJson(request, PredictorRequestData.class);
        PredictorRequestFile fileVO = new PredictorRequestFile();
        fileVO.setBytes(file.getBytes());
        fileVO.setContentType(file.getContentType());
        fileVO.setInputStream(file.getInputStream());
        fileVO.setName(file.getName());
        fileVO.setOriginalFilename(file.getOriginalFilename());
        fileVO.setSize(file.getSize());
        fileVO.setResource(file.getResource());
        PredictorRequest requestVO = new PredictorRequest();
        requestVO.setPredictorRequestData(dataVO);
        requestVO.setFileVO(fileVO);
        OcrResultVO result = predictorService.predictor(requestVO);
        return PredictorReponseResult.success(request, result);
    }


}

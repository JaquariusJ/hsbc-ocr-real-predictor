package com._4paradim.hsbc.ocr.server.web.controller;


import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.web.service.PredictorService;
import com._4paradim.hsbc.ocr.server.web.utils.PredictorReponseResult;
import com._4paradim.hsbc.ocr.server.web.vo.FileVO;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequestVO;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorResponseVO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
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

    private static final Gson gson = new Gson();

    @PostMapping("/predictor")
    public PredictorResponseVO predictor(@RequestParam("file") MultipartFile file, @RequestParam("data") String request, @RequestParam("appCode") String appCode) throws BusinessException, OcrException, IOException {
        PredictorRequestVO requestVo = gson.fromJson(request, PredictorRequestVO.class);
        FileVO fileVO = new FileVO();
        fileVO.setBasefile(new String(Base64.encodeBase64(file.getBytes())));
        fileVO.setFilename(file.getOriginalFilename());
        fileVO.setFilesize(file.getSize());
        requestVo.setFileVO(fileVO);
        String result = predictorService.predictor(requestVo);
        return PredictorReponseResult.success(request, result);
    }


}

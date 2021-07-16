package com._4paradim.hsbc.ocr.server.web.controller;


import cn.hutool.core.util.IdUtil;
import com._4paradim.hsbc.ocr.server.common.annotation.TaskTime;
import com._4paradim.hsbc.ocr.server.common.enums.TimeType;
import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com._4paradim.hsbc.ocr.server.common.exception.OcrException;
import com._4paradim.hsbc.ocr.server.common.utils.ParamValidationUtils;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@Validated
@RequestMapping("/realtime")
public class PredictorController {

    @Autowired
    private PredictorService predictorService;

    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    @PostMapping("/predictor")
    @TaskTime(type = TimeType.INTERFACE_TIME)
    public PredictorResponse predictor(@RequestParam("file") MultipartFile file, @RequestParam("data") String data, HttpServletRequest request) throws IOException {
        PredictorRequest requestVO = new PredictorRequest();
        String uuid = IdUtil.simpleUUID();
        request.setAttribute("requestId",uuid);
        requestVO.setRequestId(uuid);
        PredictorRequestData dataVO = gson.fromJson(data, PredictorRequestData.class);
        ParamValidationUtils.validate(dataVO);
        PredictorRequestFile fileVO = new PredictorRequestFile();
        fileVO.setBytes(file.getBytes());
        fileVO.setContentType(file.getContentType());
        fileVO.setInputStream(file.getInputStream());
        fileVO.setName(file.getName());
        fileVO.setOriginalFilename(file.getOriginalFilename());
        fileVO.setSize(file.getSize());
        fileVO.setResource(file.getResource());
        requestVO.setPredictorRequestData(dataVO);
        requestVO.setFileVO(fileVO);

        OcrResultVO result = predictorService.predictor(requestVO);

        return PredictorReponseResult.success(requestVO.getRequestId(),result);
    }


}

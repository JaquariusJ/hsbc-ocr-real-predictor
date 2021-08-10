package com._4paradim.hsbc.ocr.server.web.service;


import com._4paradim.hsbc.ocr.server.manager.task.AsyncTask;
import com._4paradim.hsbc.ocr.server.scene.service.SceneSocrService;
import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PredictorService {

    @Autowired
    private SceneSocrService sceneSocrService;

    @Autowired
    private AsyncTask asyncTask;


    public OcrResultVO predictor(PredictorRequest requestVo) throws Exception {
        OcrResultVO resultVo = sceneSocrService.ocr(requestVo);

        //异步发送请求，oss文档，保存数据
        //asyncTask.uploadOssAndSaveData(requestVo,resultVo);
        return resultVo;

    }


}

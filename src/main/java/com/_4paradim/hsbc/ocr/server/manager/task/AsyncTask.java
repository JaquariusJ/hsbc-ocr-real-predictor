package com._4paradim.hsbc.ocr.server.manager.task;

import com._4paradim.hsbc.ocr.server.api.service.OssService;
import com._4paradim.hsbc.ocr.server.common.annotation.TaskTime;
import com._4paradim.hsbc.ocr.server.manager.service.OcrOriginResultService;
import com._4paradim.hsbc.ocr.server.manager.service.OcrPredictorInfoService;
import com._4paradim.hsbc.ocr.server.manager.service.OcrResultItemService;
import com._4paradim.hsbc.ocr.server.manager.vo.OcrOriginResult;
import com._4paradim.hsbc.ocr.server.manager.vo.OcrPredictorInfo;
import com._4paradim.hsbc.ocr.server.manager.vo.OcrResultItem;
import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Slf4j
public class AsyncTask {

    @Autowired
    private OssService ossService;

    @Autowired
    private OcrResultItemService ocrResultItemService;

    @Autowired
    private OcrOriginResultService ocrOriginResultService;

    @Autowired
    private OcrPredictorInfoService ocrPredictorInfoService;


    @Async("threadPoolExecutor")
    @Transactional
    @TaskTime(name = "other")
    public void uploadOssAndSaveData(PredictorRequest requestVO, OcrResultVO ocrResultVO) throws IOException {
        String filename = requestVO.getFileVO().getOriginalFilename();
        String osspath = filename;
        InputStream inputStream = requestVO.getFileVO().getInputStream();
        log.info("upload to oss,file: "+filename+"  >>>  "+osspath);
        //ossService.uploadFileInputStream(inputStream,osspath);
        //保存请求的数据
        OcrPredictorInfo ocrPredictorInfo = ocrPredictorInfoService.request2PredictorInfo(requestVO);
        ocrPredictorInfo.setOssPath(osspath);
        ocrPredictorInfoService.saveOrUpdate(ocrPredictorInfo);
        //保存原始的ocr数据
        OcrOriginResult ocrOriginResult = new OcrOriginResult();
        ocrOriginResult.setId(ocrPredictorInfo.getId());
        ocrOriginResult.setOcrResult(ocrResultVO.getOrigin_result());
        ocrOriginResultService.saveOrUpdate(ocrOriginResult);
        //保存ocritem的数据
        List<OcrResultItem> ocrResultItems = ocrResultItemService.request2OcrResultItems(ocrResultVO);
        ocrResultItems.stream().forEach(n->n.setDocId(ocrPredictorInfo.getId()));
        ocrResultItemService.saveOrUpdateBatch(ocrResultItems);
    }

    @Transactional
    public void saveRunTime(){

    }

}

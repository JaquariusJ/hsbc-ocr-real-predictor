package com._4paradim.hsbc.ocr.server.manager.task;

import com._4paradim.hsbc.ocr.server.api.service.OssService;
import com._4paradim.hsbc.ocr.server.time.annotation.TaskTime;
import com._4paradim.hsbc.ocr.server.time.enums.TimeType;
import com._4paradim.hsbc.ocr.server.manager.service.OcrOriginResultService;
import com._4paradim.hsbc.ocr.server.manager.service.OcrPredictorInfoService;
import com._4paradim.hsbc.ocr.server.manager.service.OcrResultItemService;
import com._4paradim.hsbc.ocr.server.manager.vo.OcrOriginResult;
import com._4paradim.hsbc.ocr.server.manager.vo.OcrPredictorInfo;
import com._4paradim.hsbc.ocr.server.manager.vo.OcrResultItem;
import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

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
    @TaskTime(type = TimeType.ASYNC_TIME,callback = RunTimeCallBack.class,desc = "upload oss,save save request and result data,")
    public void uploadOssAndSaveData(PredictorRequest requestVO, OcrResultVO ocrResultVO) throws IOException {
        String filename = requestVO.getFileVO().getOriginalFilename();
        String osspath = "bbdm" + File.separator + LocalDate.now().toString() + File.separator +filename;
        InputStream inputStream = requestVO.getFileVO().getInputStream();
        log.info("upload to oss,file: "+filename+"  >>>  "+osspath);
        String md5 = "";
        try {
            md5 = DigestUtils.md5Hex(inputStream);
            ossService.uploadFileInputStream(inputStream,osspath);
        } catch (Exception e) {
            ExceptionUtils.printRootCauseStackTrace(e);
            log.error(e.getMessage());
        }
        //保存请求的数据
        OcrPredictorInfo ocrPredictorInfo = ocrPredictorInfoService.request2PredictorInfo(requestVO);
        ocrPredictorInfo.setId(requestVO.getRequestId());
        ocrPredictorInfo.setOssPath(osspath);
        ocrPredictorInfo.setFileMd5(md5);
        ocrPredictorInfoService.saveOrUpdate(ocrPredictorInfo);
        //保存原始的ocr数据
        try {
            OcrOriginResult ocrOriginResult = new OcrOriginResult();
            ocrOriginResult.setId(requestVO.getRequestId());
            ocrOriginResult.setOcrResult(ocrResultVO.getOrigin_result());
            ocrOriginResultService.saveOrUpdate(ocrOriginResult);
        } catch (Exception e) {
            ExceptionUtils.printRootCauseStackTrace(e);
            log.error(e.getMessage());
        }
        //保存ocritem的数据
        try {
            List<OcrResultItem> ocrResultItems = ocrResultItemService.request2OcrResultItems(ocrResultVO);
            ocrResultItems.stream().forEach(n->n.setDocId(requestVO.getRequestId()));
            ocrResultItemService.saveOrUpdateBatch(ocrResultItems);
        } catch (Exception e) {
            ExceptionUtils.printRootCauseStackTrace(e);
            log.error(e.getMessage());
        }
    }



}

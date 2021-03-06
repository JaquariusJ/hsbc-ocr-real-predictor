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
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
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

    private DigestUtils digestUtils = new DigestUtils("MD5");


    @Async("threadPoolExecutor")
    @Transactional
    @TaskTime(type = TimeType.ASYNC_TIME,callback = RunTimeCallBack.class,desc = "upload oss,save save request and result data,")
    public void uploadOssAndSaveData(PredictorRequest requestVO, OcrResultVO ocrResultVO) throws IOException {
        String filename = requestVO.getFileVO().getOriginalFilename();
        String osspath = "bbdm" + File.separator + LocalDate.now().toString() + File.separator +filename;
        InputStream inputStream = requestVO.getFileVO().getInputStream();
        log.info("upload to oss,file: "+filename+"  >>>  "+osspath);
        String md5 = "";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while((len = inputStream.read(buffer) )>-1){
            outputStream.write(buffer,0,len);
        }
        outputStream.flush();
        try {
            md5 = digestUtils.digestAsHex(new ByteArrayInputStream(outputStream.toByteArray()));
            ossService.uploadFileInputStream(new ByteArrayInputStream(outputStream.toByteArray()),osspath);
        } catch (Exception e) {
            ExceptionUtils.printRootCauseStackTrace(e);
            log.error(e.getMessage());
        }
        //?????????????????????
        OcrPredictorInfo ocrPredictorInfo = ocrPredictorInfoService.request2PredictorInfo(requestVO);
        ocrPredictorInfo.setId(requestVO.getRequestId());
        ocrPredictorInfo.setOssPath(osspath);
        ocrPredictorInfo.setFileMd5(md5);
        ocrPredictorInfoService.saveOrUpdate(ocrPredictorInfo);
        //???????????????ocr??????
        try {
            OcrOriginResult ocrOriginResult = new OcrOriginResult();
            ocrOriginResult.setId(requestVO.getRequestId());
            ocrOriginResult.setOcrResult(ocrResultVO.getOrigin_result());
            ocrOriginResultService.saveOrUpdate(ocrOriginResult);
        } catch (Exception e) {
            ExceptionUtils.printRootCauseStackTrace(e);
            log.error(e.getMessage());
        }
        //??????ocritem?????????
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

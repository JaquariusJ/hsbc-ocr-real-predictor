package com._4paradim.hsbc.ocr.server.manager.task;

import com._4paradim.hsbc.ocr.server.time.enums.TimeType;
import com._4paradim.hsbc.ocr.server.time.interfaces.CallBack;
import com._4paradim.hsbc.ocr.server.manager.service.OcrPredictorRuntimeService;
import com._4paradim.hsbc.ocr.server.manager.vo.OcrPredictorRuntime;
import com._4paradim.hsbc.ocr.server.time.vo.MethodRunTime;
import com._4paradim.hsbc.ocr.server.web.vo.PredictorRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

@Component
@Slf4j
public class RunTimeCallBack implements CallBack {

    @Autowired
    private OcrPredictorRuntimeService ocrPredictorRuntimeService;

    @Override
    public void callback(Method method, Object[] params, List<MethodRunTime> methodRunTimes) {
        Optional<Object> optional = Arrays.stream(params).filter(param -> param instanceof PredictorRequest).findFirst();
        if(!optional.isPresent()){
            String error_info = "the original method : "+method.getName()+" does not have this parameter type: PredictorRequest";
            log.error(error_info);
            throw new RuntimeException(error_info);
        }
        PredictorRequest request = (PredictorRequest) optional.get();
        String requestId = request.getRequestId();
        List<OcrPredictorRuntime> list = new ArrayList<>();
        methodRunTimes.forEach(methodRunTime -> {
            OcrPredictorRuntime ocrPredictorRuntime = new OcrPredictorRuntime();
            ocrPredictorRuntime.setDocId(requestId);
            ocrPredictorRuntime.setMessage(methodRunTime.getTimeType().getDesc());
            ocrPredictorRuntime.setClassName(methodRunTime.getClassName());
            ocrPredictorRuntime.setMethodName(methodRunTime.getMethodName());
            ocrPredictorRuntime.setTimeType(methodRunTime.getTimeType().getName());
            ocrPredictorRuntime.setMillisecond(methodRunTime.getRuntime());
            list.add(ocrPredictorRuntime);
        });
        ocrPredictorRuntimeService.saveOrUpdateBatch(list);
    }

}

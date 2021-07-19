package com._4paradim.hsbc.ocr.server.manager;

import com._4paradim.hsbc.ocr.server.manager.service.OcrPredictorRuntimeService;
import com._4paradim.hsbc.ocr.server.manager.service.OcrResultItemService;
import com._4paradim.hsbc.ocr.server.manager.vo.OcrPredictorRuntime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OcrResultItemTest {

    @Autowired
    private OcrResultItemService ocrResultItemService;

    @Autowired
    private OcrPredictorRuntimeService ocrPredictorRuntimeService;

    @Test
    public void test1(){
        System.out.println(ocrPredictorRuntimeService);
    }
}

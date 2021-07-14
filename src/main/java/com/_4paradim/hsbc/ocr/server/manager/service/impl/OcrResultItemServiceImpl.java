package com._4paradim.hsbc.ocr.server.manager.service.impl;

import com._4paradim.hsbc.ocr.server.manager.vo.OcrResultItem;
import com._4paradim.hsbc.ocr.server.manager.mapper.OcrResultItemDao;
import com._4paradim.hsbc.ocr.server.manager.service.OcrResultItemService;
import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wujian
 * @since 2021-07-13
 */
@Service
public class OcrResultItemServiceImpl extends ServiceImpl<OcrResultItemDao, OcrResultItem> implements OcrResultItemService {

    @Override
    public List<OcrResultItem> request2OcrResultItems(OcrResultVO ocrResult) {
        Map<String, Object> elements = ocrResult.getElement();
        AtomicInteger index= new AtomicInteger();
        List<OcrResultItem> list = new ArrayList<>();
        elements.forEach((k,v)->{
            OcrResultItem ocrResultItem = new OcrResultItem();
            ocrResultItem.setOcrName(k);
            ocrResultItem.setOcrValue(v == null?"":String.valueOf(v));
            ocrResultItem.setOcrTexts(ocrResult.getTexts().get(index.get()));
            ocrResultItem.setOcrTexts(ocrResult.getBoxes().get(index.get()));
            list.add(ocrResultItem);
            index.getAndIncrement();
        });
        return list;
    }

}

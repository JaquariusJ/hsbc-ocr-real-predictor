package com._4paradim.hsbc.ocr.server.manager.service;

import com._4paradim.hsbc.ocr.server.manager.vo.OcrResultItem;
import com._4paradim.hsbc.ocr.server.scene.vo.OcrResultVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wujian
 * @since 2021-07-13
 */
public interface OcrResultItemService extends IService<OcrResultItem> {

    List<OcrResultItem> request2OcrResultItems(OcrResultVO ocrResult);

}

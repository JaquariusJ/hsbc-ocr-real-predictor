package com._4paradim.hsbc.ocr.server.manager.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com._4paradim.hsbc.ocr.server.manager.vo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wujian
 * @since 2021-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OcrOriginResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * ocr_result
     */
    private String ocrResult;


}

package com._4paradim.hsbc.ocr.server.manager.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com._4paradim.hsbc.ocr.server.manager.vo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wujian
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OcrResultItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long docId;

    /**
     * ocr
     */
    private String ocrName;

    /**
     * ocr
     */
    private String ocrValue;

    /**
     * ocrboxes
     */
    private String ocrBoxes;

    /**
     * ocrtexts
     */
    private String ocrTexts;

    /**
     * 1
     */
    private String comment;

    /**
     * 2
     */
    private String valueType;

    /**
     * 3
     */
    private String value;


}

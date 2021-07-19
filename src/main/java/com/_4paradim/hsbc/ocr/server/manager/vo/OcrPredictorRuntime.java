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
 * @since 2021-07-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OcrPredictorRuntime extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String docId;

    /**
     * time_type
     */
    private String timeType;

    /**
     * class_name
     */
    private String className;

    /**
     * method_name
     */
    private String methodName;

    /**
     * message
     */
    private String message;

    /**
     * millisecond
     */
    private Long millisecond;


}

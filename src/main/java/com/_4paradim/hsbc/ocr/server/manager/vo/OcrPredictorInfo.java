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
public class OcrPredictorInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * doc_type
     */
    private String docType;

    /**
     * doc_sub_type
     */
    private String subType;

    /**
     * file_size
     */
    private Long fileSize;

    /**
     * file_type
     */
    private String fileType;

    /**
     * file_name
     */
    private String fileName;

    /**
     * file_md5
     */
    private String fileMd5;

    /**
     * doc_data
     */
    private String ossPath;

    /**
     * doc_data
     */
    private String data;


}

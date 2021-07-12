package com._4paradim.hsbc.ocr.server.web.vo;

import com._4paradim.hsbc.ocr.server.web.types.DocType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.Assert;

@Data
public class FileVO {

    private String filename;

    private Long filesize;

    private String basefile;

}

package com._4paradim.hsbc.ocr.server.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OcrException extends RuntimeException {

    private String message;
}

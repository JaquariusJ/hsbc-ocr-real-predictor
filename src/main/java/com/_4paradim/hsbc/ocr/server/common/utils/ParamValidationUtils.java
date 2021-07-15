package com._4paradim.hsbc.ocr.server.common.utils;

import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import java.util.Set;

public class ParamValidationUtils {
    public static void validate(@Valid Object user) {
        Set<ConstraintViolation<@Valid Object>> validateSet = Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(user, new Class[0]);
        if (!CollectionUtils.isEmpty(validateSet)) {
            String messages = validateSet.stream()
                    .map(ConstraintViolation::getMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);
 
        }
    }
}
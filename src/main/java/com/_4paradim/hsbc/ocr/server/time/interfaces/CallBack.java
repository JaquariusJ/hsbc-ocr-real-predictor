package com._4paradim.hsbc.ocr.server.time.interfaces;

import com._4paradim.hsbc.ocr.server.time.enums.TimeType;
import com._4paradim.hsbc.ocr.server.time.vo.MethodRunTime;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public interface CallBack {

    /**
     *
     * @param method 被注解修饰的方法
     * @param params 方法实际的参数
     * @param list  统计时间的结果
     */
    void callback(Method method, Object[] params, List<MethodRunTime> list);
}

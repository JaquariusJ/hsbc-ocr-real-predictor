package com._4paradim.hsbc.ocr.server.scene.service;

import com._4paradim.hsbc.ocr.server.common.annotation.TaskTime;

/**
 * 场景预估接口
 * @param <T>
 * @param <R>
 */
public interface SceneService<T,R> {

    T ocr(R t) throws Exception;

}

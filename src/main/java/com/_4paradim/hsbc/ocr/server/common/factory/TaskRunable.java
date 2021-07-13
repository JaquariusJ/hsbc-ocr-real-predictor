package com._4paradim.hsbc.ocr.server.common.factory;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskRunable {

    @Resource(name = "threadPoolExecutor")
    private ExecutorService executorService;

//    public void runTask(){
//        executorService.submit();
//    }
}

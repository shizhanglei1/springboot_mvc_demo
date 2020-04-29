package com.zjg.tomdog.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadExceptionHandle implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("the task is so mush that overflow，we will give up this task," +
                "we will accept new task until the blockingqueue have free! task is :" +r);
    }
}

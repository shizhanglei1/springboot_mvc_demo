package com.zjg.tomdog.handlecoreimpl;

import com.zjg.tomdog.handlecore.ExceptionHandle;

public class ExceptionHandleImpl implements ExceptionHandle {
    @Override
    public String noFoundPath(String pathMsg) {
        System.out.println("fail to lookup"+pathMsg);
        return "404.html";
    }

    @Override
    public String cantHandlePath(String errorMsg) {
        System.out.println(errorMsg);
        return "500.html";
    }
}

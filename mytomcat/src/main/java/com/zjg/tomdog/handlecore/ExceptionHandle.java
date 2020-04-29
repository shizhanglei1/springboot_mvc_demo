package com.zjg.tomdog.handlecore;

public interface ExceptionHandle {
    /**
     * handlecoreimpl 404 exception
     * @param pathMsg
     * @return
     */
    public String noFoundPath(String pathMsg);

    /**
     * 500 exception
     * @param errorMsg
     * @return
     */
    public String cantHandlePath(String errorMsg);
}

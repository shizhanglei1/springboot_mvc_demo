package com.zjg.tomdog.handlecore;

import com.zjg.tomdog.util.Request;

import java.net.Socket;

/**
 * 处理请求核心接口
 */
public interface HandleCore {

    /**
     * 读取请求的信息，获取到需要的数据
     * @param socket
     * @return
     */
    public Request read(Socket socket);

    /**
     * 处理分析request，得到请求的资源路径
     * @param socket
     * @param request
     */
    public void forword(Socket socket,Request request);

    /**
     * 关闭输入流套接字
     * @param socket
     */
    public void closeSocket(Socket socket);

    /**
     * close tomdog
     */
    public void closeTomdog();
}

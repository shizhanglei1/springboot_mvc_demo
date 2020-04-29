package com.zjg.tomdog.thread;

import com.zjg.tomdog.handlecore.ExceptionHandle;
import com.zjg.tomdog.handlecore.HandleCore;
import com.zjg.tomdog.handlecoreimpl.ExceptionHandleImpl;
import com.zjg.tomdog.handlecoreimpl.HandleCoreImpl;
import com.zjg.tomdog.util.Request;

import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

/**
 *  Tomcat内部处理线程
 */
public class ThreadTask extends Thread {
    private Socket socket = null;
    //核心方法类
    private final HandleCore handleCore = new HandleCoreImpl();
    private final ExceptionHandle exceptionHandle = new ExceptionHandleImpl();
    public ThreadTask(Socket socket){
        this.socket = socket;
    }
    public void run(){
        int timeOut = 3; //设置超时时间为3秒
        Date startDate = new Date(); //第一次进入时间
        Date endDate;
        Request requestMes = null;
        while(true){
            endDate = new Date();
            if((startDate.getTime()-endDate.getTime())/1000 > timeOut){
                handleCore.closeSocket(socket);
                break;
            }else{
                startDate = endDate;
            }
            try {
                socket.setSoTimeout(3*1000);
                requestMes = handleCore.read(socket);
                if(requestMes != null){
                    handleCore.forword(socket,requestMes);
                }else {
                    handleCore.closeSocket(socket);
                    break;
                }
            } catch (SocketException e) {
                e.printStackTrace();
                exceptionHandle.cantHandlePath("接收请求或关闭socket异常");
                handleCore.closeSocket(socket);
                break;
            }


        }



    }


}

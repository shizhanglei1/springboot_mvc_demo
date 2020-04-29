package com.zjg.tomdog.handlecoreimpl;

import com.zjg.tomdog.MainApp;
import com.zjg.tomdog.handlecore.HandleCore;
import com.zjg.tomdog.util.JudgeSource;
import com.zjg.tomdog.util.Request;
import com.zjg.tomdog.util.Response;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Map;

public class HandleCoreImpl implements HandleCore {
    ExceptionHandleImpl exceptionHandle = new ExceptionHandleImpl(); //异常处理
    @Override
    public Request read(Socket socket) {
        String url = null;
        try {
            InputStream inputStream = new BufferedInputStream(socket.getInputStream());
            byte reads = (byte)inputStream.read();
            byte[] bts = new byte[inputStream.available()+1];
            bts[0] = reads;
            inputStream.read(bts,1,inputStream.available());
            url = new String(bts);
        }catch (SocketTimeoutException ex) {
            System.out.println("Read timed out");
            new ExceptionHandleImpl().cantHandlePath("连接超时");
            return null;
        } catch (IOException e) {
            new ExceptionHandleImpl().cantHandlePath("连接超时");
            return null;
        }
        Request requestMes = null;
        if(url != null){
            requestMes = new Request(url,socket);
        }
        return requestMes;
    }

    @Override
    public void forword(Socket socket, Request request) {
        boolean isStatic = new JudgeSource().judgeSourceType(request);
        String path = null;
        if(!isStatic){
            Map<Class<?>,String> allServlet = MainApp.servlets;
            String requestData = request.getData();
            String requestMethod = request.getMethod();
            Method method = null;
            Class<?> claes = null;
            boolean isExit = false;
            Response response = new Response();
            for(Map.Entry<Class<?>,String> servlets : allServlet.entrySet()){
                String servletName = servlets.getKey().getSimpleName();
                if(requestData.equals(servletName)){
                    isExit = true;
                    try {
                        claes = Class.forName(servlets.getKey().getName());
                        if("GET".equals(requestMethod)){
                            method = claes.getMethod("doGet",Request.class,Response.class);
                        }else if("POST".equals(requestMethod)){
                            method = claes.getMethod("doPost",Request.class,Response.class);
                        }
                        //assert [boolean 表达式]
                        //如果[boolean表达式]为true，则程序继续执行。
                        //如果为false，则程序抛出AssertionError，并终止执行。
                        assert method != null;
                        method.invoke(claes.newInstance(),request,response);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        path = exceptionHandle.noFoundPath("ClassNotFoundException: 反射机制加载类失败，该类不存在");
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        path = exceptionHandle.cantHandlePath("NoSuchMethodException: 不存在这样的方法");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        path = exceptionHandle.cantHandlePath("反射机制使用异常");
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        path = exceptionHandle.cantHandlePath("反射机制使用异常");
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        path = exceptionHandle.cantHandlePath("反射机制使用异常");
                    }
                }
            }
            if(!isExit){
                path = exceptionHandle.noFoundPath(requestData);
            }
        }else {
            System.out.println("静态资源");
            path = request.getData();
        }
        if(path != null){
            new Response().write(socket,path);
        }

    }

    @Override
    public void closeSocket(Socket socket) {
        try {
            if (!socket.isInputShutdown()) {
                System.out.println("关闭输入流");
                socket.shutdownInput();
            }
            if (!socket.isOutputShutdown()) {
                System.out.println("关闭输出流");
                socket.shutdownOutput();
            }
            if (!socket.isClosed()) {
                System.out.println("关闭socket");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeTomdog() {

    }
}

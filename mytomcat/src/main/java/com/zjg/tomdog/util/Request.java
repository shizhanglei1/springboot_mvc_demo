package com.zjg.tomdog.util;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 接收到的http信息
 */
public class Request {
    private String protocol; //协议类型
    private String data;//请求的数据
    private String method;//请求方法（get/post）
    private Map<String,String> attrs; //参数
    private Socket socket;

    /**
     * 请求方法
     * @param protocol
     * @param data
     * @param method
     */
    public Request(String protocol, String data, String method) {
        this.protocol = protocol;
        this.data = data;
        this.method = method;
    }

    /**
     * 请求方法
     * @param request
     * @param socket
     */
    public  Request(String request,Socket socket){
        this.getRequestMess(request);
        this.socket = socket;
    }

    private void getRequestMess(String request){
        String[] methodUrlPro = request.split("\n")[0].split(" ");
        if(methodUrlPro.length == 3){
            this.method = methodUrlPro[0];
            this.protocol = methodUrlPro[2];
            String[] urlAttr = methodUrlPro[1].split("\\?");//分隔参数和url
            this.data = urlAttr[0].substring(1,urlAttr[0].length());
            String[] attrArray,keyValue;//所有参数，每个参数的参数名和值
            if(urlAttr.length>1){
                attrArray = urlAttr[1].split("&");
                for(String s : attrArray){
                    keyValue = s.split("=");
                    this.addAttrs(keyValue[0],keyValue[1]);
                }

            }
        }
    }

    private void addAttrs(String s, String s1) {
        if(attrs == null){
            attrs = new HashMap<String,String>();
        }
        this.attrs.put(s,s1);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String toString() {
        return "Request{" +
                "protocol='" + protocol + '\'' +
                ", data='" + data + '\'' +
                ", method='" + method + '\'' +
                ", attrs=" + attrs +
                ", socket=" + socket +
                '}';
    }
}

package com.zjg.tomdog.util;

import com.zjg.tomdog.handlecore.ExceptionHandle;
import com.zjg.tomdog.handlecore.HandleCore;
import com.zjg.tomdog.handlecoreimpl.ExceptionHandleImpl;
import com.zjg.tomdog.handlecoreimpl.HandleCoreImpl;

import java.io.*;
import java.net.Socket;

public class Response{
    /**
     * 做出响应给客户端
     * @param socket
     * @param path
     */
    public void write(Socket socket,String path ){
        ExceptionHandle exceptionHandle = new ExceptionHandleImpl();
        HandleCore handleCore = new HandleCoreImpl();
        File file = null;
        if(path==null){
            exceptionHandle.cantHandlePath("请求的信息为空");
        }
        //获得程序当前路径
        path = System.getProperty("user.dir")+"\\mytomcat\\webapp"+"\\"+path;
        file = new File(path);
        try {
            //拿到path路径下资源，写入IO 流
            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = socket.getOutputStream();
            byte[] bytes = new byte[1024];
            int read;
            while((read = fileInputStream.read(bytes,0,1024)) != -1){

                outputStream.write(("HTTP/1.1 200 OK\r\n" +  //响应头第一行
                        "Content-Type: text/html; charset=utf-8\r\n" +  //简单放一个头部信息
                        "\r\n" +  //这个空行是来分隔请求头与请求体的
                        "<h1>这是响应报文</h1>\r\n").getBytes());
                outputStream.write(bytes,0,read);
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            exceptionHandle.noFoundPath("无法得到outStream");
        }
    }
}



/**
*
java.version          Java 运行时环境版本
java.vendor         Java 运行时环境供应商
java.vendor.url         Java 供应商的 URL
java.vm.specification.version         Java 虚拟机规范版本
java.vm.specification.vendor         Java 虚拟机规范供应商
java.vm.specification.name         Java 虚拟机规范名称
java.vm.version         Java 虚拟机实现版本
java.vm.vendor         Java 虚拟机实现供应商
java.vm.name         Java 虚拟机实现名称
java.specification.version         Java 运行时环境规范版本
java.specification.vendor         Java 运行时环境规范供应商
java.specification.name         Java 运行时环境规范名称
os.name         操作系统的名称
os.arch         操作系统的架构
os.version         操作系统的版本
file.separator         文件分隔符（在 UNIX 系统中是“ / ”）
path.separator         路径分隔符（在 UNIX 系统中是“ : ”）
line.separator         行分隔符（在 UNIX 系统中是“ /n ”）

java.home         Java 安装目录
java.class.version         Java 类格式版本号
java.class.path         Java 类路径
java.library.path          加载库时搜索的路径列表
java.io.tmpdir         默认的临时文件路径
java.compiler         要使用的 JIT 编译器的名称
java.ext.dirs         一个或多个扩展目录的路径
user.name         用户的账户名称
user.home         用户的主目录
user.dir


public class Test {

    public static void main(String[] args) throws MalformedURLException, URISyntaxException {

        System.out.println("java.home : "+System.getProperty("java.home"));

        System.out.println("java.class.version : "+System.getProperty("java.class.version"));

        System.out.println("java.class.path : "+System.getProperty("java.class.path"));

        System.out.println("java.library.path : "+System.getProperty("java.library.path"));

        System.out.println("java.io.tmpdir : "+System.getProperty("java.io.tmpdir"));

        System.out.println("java.compiler : "+System.getProperty("java.compiler"));

        System.out.println("java.ext.dirs : "+System.getProperty("java.ext.dirs"));

        System.out.println("user.name : "+System.getProperty("user.name"));

        System.out.println("user.home : "+System.getProperty("user.home"));

        System.out.println("user.dir : "+System.getProperty("user.dir"));

        System.out.println("===================");

        System.out.println("package: "+Test.class.getPackage().getName());

        System.out.println("package: "+Test.class.getPackage().toString());

        System.out.println("=========================");

        String packName = Test.class.getPackage().getName();

                /*URL packurl = new URL(packName);
                System.out.println(packurl.getPath());

        URI packuri = new URI(packName);

        System.out.println(packuri.getPath());

        //System.out.println(packuri.toURL().getPath());

        System.out.println(packName.replaceAll("//.", "/"));

        System.out.println(System.getProperty("user.dir")+"/"+(Test.class.getPackage().getName()).replaceAll("//.", "/")+"/");

    }

}
 ***/

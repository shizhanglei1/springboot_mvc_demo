package com.zjg.tomdog.handle;

import com.zjg.tomdog.thread.ThreadPoolFactory;
import com.zjg.tomdog.thread.ThreadTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Handle {
    /**
     *监听并处理请求
     */
    public void handle(){
        ThreadPoolFactory threadPoolFactory = ThreadPoolFactory.getInstance();
        try {
            ServerSocket serverSocket = new ServerSocket(8080); //监听80端口
            while (true){
                //等待请求
                Socket socket = serverSocket.accept();//当服务器接受一个请求时就创建一个socket,如果没有接收就堵塞在这里
                threadPoolFactory.addTask(new ThreadTask(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


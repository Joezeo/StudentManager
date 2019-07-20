package com.joezeo.core;

import com.joezeo.message.ResponseMessage;
import com.joezeo.utils.CloseUtils;

import java.io.*;
import java.net.Socket;

/**
 * 负责服务器端与客户端通信的类
 * 实现多线程 可以允许多个客户端与服务器端建立连接
 * 包含输入、输出流（从客户端socket获取）
 */
public class Channel implements Runnable {
    /**
     * 输入流 接收从客户端传来的信息
     */
    private ObjectInputStream is;

    /**
     * 输出流 给客户端发送信息
     */
    private ObjectOutputStream os;

    /**
     * 处理请求的对象
     */
    private Request request;

    /**
     * 处理响应的对象
     */
    private Response response;

    /**
     * 构造方法 获取对应客户端socket的输入、输出流
     * 初始化处理请求和响应的对象
     *
     * @param client
     */
    public Channel(Socket client) {
        try {
            is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
            os = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));

            request = new Request(is);
            response = new Response(os);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取输入、输出流失败 当前线程：" + Thread.currentThread().getName());
        }
    }

    /**
     * 各个线程主函数
     */
    @Override
    public void run() {
        //处理请求
        ResponseMessage resMsg = request.handleRequest();
        //处理响应
        response.handleResponse(resMsg);
        //停止此Channel 释放资源
        stopChannel();
    }

    /**
     * 停止此Channel 释放资源
     */
    private void stopChannel() {
        CloseUtils.close(os, is);
    }
}

package com.joezeo.core;

import com.joezeo.message.ResponseMessage;
import com.joezeo.utils.CloseUtils;

import java.net.Socket;

/**
 * 负责服务器端与客户端通信的类
 * 实现多线程 可以允许多个客户端与服务器端建立连接
 * 包含输入、输出流（从客户端socket获取）
 */
public class Channel implements Runnable {
    /**
     * 客户端套接字
     */
    private Socket client;

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
        this.client = client;
    }

    /**
     * 各个线程主函数
     */
    @Override
    public void run() {
        request = new Request(client);
        //处理请求
        ResponseMessage resMsg = request.handleRequest();

        response = new Response(client);
        //处理响应
        response.handleResponse(resMsg);
    }
}

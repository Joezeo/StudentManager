package com.joezeo.core;

import com.joezeo.message.RequestMessage;
import com.joezeo.message.ResponseMessage;

import java.io.*;
import java.net.Socket;

/**
 * 负责客户端和服务器端通信的类
 * 包含输入流、输出流
 */
public class Channel {
    /**
     * 客户端套接字对象
     */
    private Socket client;

    /**
     * 客户端处理请求的对象
     */
    private Request request;

    /**
     * 接收响应的对象
     */
    private Response response;

    /**
     * 请求、响应对象
     */
    private RequestMessage reqMsg;
    private ResponseMessage resMsg;

    /**
     * 构造方法：
     *
     * @param client 用户端套接字对象
     */
    public Channel(Socket client) {
        this.client = client;
    }

    /**
     * 构造方法
     *
     * @param client 用户端套接字对象
     * @param reqMsg 请求信息
     */
    public Channel(Socket client, RequestMessage reqMsg) {
        this(client);
        this.reqMsg = reqMsg;
    }

    /**
     * 公共方法：
     * 执行发送请求、获取响应等相关操作
     */
    public void run() {
        request = new Request(client, reqMsg);
        request.handleRequest();

        response = new Response(client);
        response.handleResponse();
    }
}

package com.joezeo.core;

import com.joezeo.message.ResponseMessage;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 处理服务器端响应的类
 */
public class Response {
    /**
     * 输入流 从Channel类中获取再传入到这个类中
     */
    private ObjectOutputStream os;

    /**
     * 响应消息对象 将此对象发回给客户端
     */
    private ResponseMessage msg;

    /**
     * 构造函数
     * 初始化输出流
     *
     * @param client 客户端套接字对象
     */
    public Response(Socket client) {
        try {
            this.os = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取输出流失败");
        }
    }

    /**
     * 向外界提供的公共方法：
     * 包含对服务器端响应信息的一系列操作
     */
    public void handleResponse(ResponseMessage msg) {
        this.msg = msg;
        sendResponse();
        System.out.println("响应用户请求：" + msg);
    }

    private void sendResponse() {
        try {
            os.writeObject(msg);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("发送响应信息失败 当前线程：" + Thread.currentThread().getName());
        }
    }
}

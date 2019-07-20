package com.joezeo.core;

import com.joezeo.message.RequestMessage;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 处理客户端请求的类
 */
public class Request {
    /**
     * 输出流 向服务器端发送请求
     */
    private ObjectOutputStream os;

    /**
     * 请求信息
     */
    private RequestMessage msg;

    public Request(Socket client, RequestMessage msg) {
        try {
            this.os = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取输出流失败~");
        }

        this.msg = msg;
    }

    /**
     * 公共方法：
     */
    public void handleRequest() {
        try {
            os.writeObject(msg);
            os.flush();
            System.out.println("发送成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

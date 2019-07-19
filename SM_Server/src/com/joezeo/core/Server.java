package com.joezeo.core;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    /**
     * 服务器套接字socket对象
     */
    private static ServerSocket server;

    /**
     * 静态初始化块：初始化服务器socket
     */
    static {
        try {
            server = new ServerSocket(8888);
            System.out.println("MESSAGE：初始化服务器成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR：初始化服务器失败");
        }
    }

    /**
     * 服务器main函数入口
     * @param args 参数列表
     */
    public static void main(String[] args) {
        try {
            server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

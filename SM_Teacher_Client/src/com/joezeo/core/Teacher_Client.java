package com.joezeo.core;

import java.io.IOException;
import java.net.Socket;

/**
 * 学生管理系统教师客户端
 */
public class Teacher_Client {
    /**
     * 教师客户端套接字socket 使用单例模式
     */
    private static Socket teacherClient;
    //私有化构造器

    /**
     * 静态初始化块 初始化教师端套接字对象
     */
    static {
        try {
            teacherClient = new Socket("localhost", 8888);
            System.out.println("MESSAGE：初始化教师客户端成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR：初始化教师客户端失败");
        }
    }
    /**
     * 教师端main函数入口
     * @param args
     */
    public static void main(String[] args) {

    }
}

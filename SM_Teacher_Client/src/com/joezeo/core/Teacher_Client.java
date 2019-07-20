package com.joezeo.core;

import com.joezeo.message.RequestMessage;
import com.joezeo.scene.Scene;

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
    private Teacher_Client(){
    }

    /**
     * 客户端是否在运行
     */
    private static boolean isRunning = false;

    /**
     * 静态初始化块 初始化教师端套接字对象
     */
    static {
        try {
            teacherClient = new Socket("localhost", 8888);
            isRunning = true;
            System.out.println("MESSAGE：初始化教师客户端成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR：初始化教师客户端失败");
        }
    }

    /**
     * 教师端main函数入口
     *
     * @param args
     */
    public static void main(String[] args) {
        boolean flag = Scene.welcomeScene();
        RequestMessage msg = null;
        if(flag){
            msg = Scene.login();
        } else {
            msg = Scene.register();
        }
        Channel channel = new Channel(teacherClient, msg);

        while(isRunning){
            channel.run();
        }
    }
}

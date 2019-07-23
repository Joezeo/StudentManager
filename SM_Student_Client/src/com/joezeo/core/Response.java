package com.joezeo.core;

import com.joezeo.Scene.Scene;
import com.joezeo.message.ResponseMessage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Response {
    /**
     * 接收从服务器端发来的响应信息
     */
    private ObjectInputStream is;

    /**
     * 响应信息
     */
    private ResponseMessage msg;

    /**
     * 是否已经登陆、注册
     */
    private boolean isLogin = false;
    private boolean isRegister = false;


    public Response(Socket client) {
        try {
            this.is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取输入流失败");
        }
    }

    /**
     * 处理从服务器端发来的Response信息
     */
    public void handleResponse(){
        try {
            Object obj = is.readObject();
            if(obj instanceof ResponseMessage){
                this.msg = (ResponseMessage)obj;

                if(msg.getIsLogin() != null){//表示此时进行的操作为登陆操作
                    if(msg.getIsLogin().equals("true")){//登陆成功
                        Scene.loginSuccessScene();
                        isLogin = true;
                    } else if(msg.getIsLogin().equals("false")){//登陆失败 返回欢迎界面
                        Scene.loginFailedScene();
                        isLogin = false;
                    }
                } else if(msg.getIsRegister() != null){//表示此时进行的操作为注册操作
                    if(msg.getIsRegister().equals("true")){//注册成功
                        Scene.registerSuccessScene();
                        isRegister = true;
                    } else if(msg.getIsRegister().equals("false")){//注册失败 返回欢迎界面
                        Scene.registerFailedScene();
                        isRegister = false;
                    }
                } else if(msg.getInquiryContent() != null){//表示此时进行的操作为查询操作
                    Scene.inquiryResultScene(msg.getInquiryContent());
                } else {
                    System.out.println("ERROR：接收到空的响应消息");
                    System.out.println("请按任意键继续");
                    new Scanner(System.in).next();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }
}

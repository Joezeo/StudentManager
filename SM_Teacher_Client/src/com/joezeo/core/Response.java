package com.joezeo.core;

import com.joezeo.message.ResponseMessage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * 处理接收响应的类
 */
public class Response {
    /**
     * 接收从服务器端发来的响应信息
     */
    private ObjectInputStream is;

    /**
     * 响应信息
     */
    private ResponseMessage msg;

    public Response(Socket client) {
        try {
            this.is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取输入流失败");
        }
    }

    public void handleResponse(){
        try {
            Object obj = is.readObject();
            if(obj instanceof ResponseMessage){
                this.msg = (ResponseMessage)obj;
            }
            System.out.println("接收成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

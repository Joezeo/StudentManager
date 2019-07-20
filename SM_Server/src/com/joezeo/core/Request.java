package com.joezeo.core;

import com.joezeo.message.RequestMessage;
import com.joezeo.message.ResponseMessage;
import com.joezeo.opration.Opration;
import com.joezeo.utils.ReflectionUtils;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 处理客户端请求信息的类
 * 将处理后的信息放入类Message中
 */
public class Request {
    /**
     * 输入流 从Channel类中获取再传入到这个类中
     */
    private ObjectInputStream is;

    /**
     * requestMessage 从客户端发出，服务器输入流中获取
     */
    private RequestMessage msg;

    /**
     * 构造函数
     *
     * @param is 与客户端相关的输入流
     */
    public Request(ObjectInputStream is) {
        this.is = is;
    }

    /**
     * 向外界提供的公共方法
     * 包含对客户端请求信息的一系列操作
     */
    public ResponseMessage handleRequest() {
        getRequestMessage();

        //根据msg内容进行相应的反射操作
        ResponseMessage resMsg = doReflection();

        return resMsg;
    }

    /**
     * 从服务器输入流中获取客户端发来的请求信息 存入msg中
     */
    private void getRequestMessage() {
        try {
            Object obj = is.readObject();
            if (obj instanceof RequestMessage) {
                msg = (RequestMessage) obj;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR：获取客户端请求失败 当前线程：" + Thread.currentThread().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ResponseMessage doReflection() {
        String methodName = msg.getOprationName();
        String character = msg.getCharacter();

        Class<Opration> clazz = null;
        Opration opration = null;

        //根据character信息获取不同的class对象
        clazz = ReflectionUtils.getClassFromCharacter(character);

        //执行methodName相应的方法
        opration = ReflectionUtils.invokeOpration(methodName, clazz, msg);

        return opration.getMsg();
    }
}

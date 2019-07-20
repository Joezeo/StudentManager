package com.joezeo.core;

import com.joezeo.message.ResponseMessage;

import java.io.ObjectOutputStream;

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
     *
     * @param os 与客户端相关的输入流
     */
    public Response(ObjectOutputStream os) {
        this.os = os;
    }

    /**
     * 向外界提供的公共方法
     * 包含对服务器端响应信息的一系列操作
     */
    public void handleResponse(ResponseMessage msg) {
        this.msg = msg;
    }
}

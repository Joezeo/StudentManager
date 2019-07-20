package com.joezeo.message;

import java.io.Serializable;

public class ResponseMessage implements Message, Serializable {
    /**
     * 是否登陆成功 只有在进行登陆操作时此值不为空
     * 用字符串true false表示是否登陆成功
     */
    private String isLogin;

    /**
     * 是否注册成功 只有在进行注册操作时此值不为空
     * 用字符串true false表示是否注册成功
     */
    private String isRegister;

    /**
     * 查询后获取的内容 只有在进行查询操作时候此值不为空
     */
    private String inquiryContent;

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

    public String getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(String isRegister) {
        this.isRegister = isRegister;
    }

    public String getInquiryContent() {
        return inquiryContent;
    }

    public void setInquiryContent(String inquiryContent) {
        this.inquiryContent = inquiryContent;
    }

    public ResponseMessage() {
    }
}

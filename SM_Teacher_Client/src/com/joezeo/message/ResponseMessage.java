package com.joezeo.message;

public class ResponseMessage implements Message {
    /**
     * 是否登陆成功 只有在进行登陆操作时才有此值
     */
    private boolean isLogin;

    /**
     * 是否注册成功 只有在进行注册操作时才有此值
     */
    private boolean isRegister;

    /**
     * 查询后获取的内容
     */
    private String inquiryContent;

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

    public String getInquiryContent() {
        return inquiryContent;
    }

    public void setInquiryContent(String inquiryContent) {
        this.inquiryContent = inquiryContent;
    }

    public ResponseMessage(boolean isLogin, boolean isRegister, String inquiryContent) {
        this.isLogin = isLogin;
        this.isRegister = isRegister;
        this.inquiryContent = inquiryContent;
    }

    public ResponseMessage() {
    }
}

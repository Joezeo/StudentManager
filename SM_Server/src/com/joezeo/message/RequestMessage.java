package com.joezeo.message;

/**
 * requestMessage 从客户端发出，服务器输入流中获取
 */
public class RequestMessage implements Message {
    /**
     * 需要执行操作的方法名
     */
    private String oprationName;

    /**
     * 学号/教师号 用于作为登陆名
     */
    private Integer id;

    /**
     * 登陆密码 只在进行登陆操作的时候传入 其他时候为null
     */
    private String pwd;

    /**
     * 发送此请求信息的客户端的身份
     * teacher or student
     */
    private String character;

    /**
     * 姓名
     */
    private String name;

    public String getOprationName() {
        return oprationName;
    }

    public void setOprationName(String oprationName) {
        this.oprationName = oprationName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RequestMessage(String oprationName, Integer id, String pwd, String character) {
        this.oprationName = oprationName;
        this.id = id;
        this.pwd = pwd;
        this.character = character;
    }

    public RequestMessage(String oprationName, Integer id, String character) {
        this.oprationName = oprationName;
        this.id = id;
        this.character = character;
    }

    public RequestMessage() {
    }
}

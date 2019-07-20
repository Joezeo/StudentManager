package com.joezeo.opration;

import com.joezeo.message.ResponseMessage;

/**
 * 学生管理系统的操作方法接口
 * 包含教师端和学生端对数据库数据的操作方法
 * 由服务器端通过SM_DB下的JDBC框架对数据库建立连接
 * 此接口下的方法与数据库操作息息相关
 */
public abstract class Opration {
    /**
     * 将数据库处理结果 存储在此对象中
     * 用protectedd修饰子类可以访问此对象
     */
    protected ResponseMessage msg;

    /**
     * 构造方法 初始化响应消息类
     */
    public Opration() {
        msg = new ResponseMessage();
    }

    public ResponseMessage getMsg() {
        return msg;
    }

    /**
     * 登陆操作
     *
     * @param id  学号\教师号作为登陆名
     * @param pwd 密码
     * @return 是否登陆操作
     */
    abstract void login(Integer id, String pwd);

    /**
     * 注册操作
     * 学生在进行注册时 其注册信息必须与数据库表sreginfo中的信息匹配
     *
     * @param id  学号\教师号作为登陆名
     * @param pwd 密码
     * @return 是否注册成功
     */
    abstract void register(Integer id, String pwd);

    /**
     * 教师端操作：
     * 教师端将学生的学号、姓名存入数据库中
     * 学生在注册时 id和name必须和数据库中的信息匹配
     * 相应的po类：Sreginfo
     *
     * @param id
     * @param name
     */
    abstract void addStudent(Integer id, String name);

    /**
     * 学生端操作：
     * 学生端通过id（主键）查询成绩
     *
     * @param id 学号
     */
    abstract void inquiry(Integer id);
}

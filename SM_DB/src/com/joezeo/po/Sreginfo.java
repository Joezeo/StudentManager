package com.joezeo.po;

/**
 * po类：保存学生的注册信息
 */
public class Sreginfo {
    /**
     * 学号 表主键 用作登陆名
     */
    private Integer stuId;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 登陆密码
     */
    private String pwd;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Sreginfo(Integer stuId, String name) {
        this.stuId = stuId;
        this.name = name;
    }

    public Sreginfo(Integer stuId, String name, String pwd) {
        this.stuId = stuId;
        this.name = name;
        this.pwd = pwd;
    }

    public Sreginfo() {
    }
}

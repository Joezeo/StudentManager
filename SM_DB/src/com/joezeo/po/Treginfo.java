package com.joezeo.po;

/**
 * po类：保存教师的注册信息
 */
public class Treginfo {
    /**
     * 教师号 表主键 用作登陆名
     */
    private Integer teaId;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 登陆密码
     */
    private String pwd;

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
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

    public Treginfo(Integer teaId, String name, String pwd) {
        this.teaId = teaId;
        this.name = name;
        this.pwd = pwd;
    }

    public Treginfo() {
    }
}

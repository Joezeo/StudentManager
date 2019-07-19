package com.joezeo.po;

/**
 * po类 ：学生
 */
public class Student {
    /**
     * 学号：为数据库主键
     */
    private int stuId;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生性别
     */
    private String sex;

    /**
     * 学生年龄
     */
    private int age;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

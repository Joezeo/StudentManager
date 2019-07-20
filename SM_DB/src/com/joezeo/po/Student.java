package com.joezeo.po;

/**
 * po类 ：学生的具体信息 与数据库中的表对应
 */
public class Student {
    /**
     * 学号：为数据库主键 且作为登陆账号
     */
    private Integer stuId;

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
    private Integer age;

    /**
     * 学生成绩
     */
    private Integer score;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

package com.joezeo.opration;

/**
 * 学生端操作实现类
 */
public class SOpration extends Opration {
    /*
    父类中包含ResponseMessage属性
    子类中再进行了数据库操作之后将从数据库中获取的信息存入message中
    */

    @Override
    public void login(Integer id, String pwd) {
    }

    @Override
    public void register(Integer id, String pwd, String name) {
    }

    @Override
    public void addStudent(Integer id, String name, Integer score) {
        //学生端不实现此方法
    }

    @Override
    public void inquiry(Integer id) {

    }

    public SOpration() {
    }
}

package com.joezeo.opration;

/**
 * 教师端操作实现类
 */
public class TOpration extends Opration {
    /*
    父类中包含ResponseMessage属性
    子类中再进行了数据库操作之后将从数据库中获取的信息存入message中
     */

    @Override
    public void login(Integer id, String pwd) {
    }

    @Override
    public void register(Integer id, String pwd) {
    }

    @Override
    public void addStudent(Integer id, String name) {

    }

    @Override
    public void inquiry(Integer id) {

    }

    public TOpration() {
    }
}

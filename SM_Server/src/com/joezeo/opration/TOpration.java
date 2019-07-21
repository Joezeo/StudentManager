package com.joezeo.opration;

import com.joezeo.core.Query;
import com.joezeo.core.QueryFactory;

/**
 * 教师端操作实现类
 */
public class TOpration extends Opration {
    /*
    父类中包含 ResponseMessage 属性
    子类中再进行了数据库操作之后将从数据库中获取的信息存入message中
     */

    @Override
    public void login(Integer id, String pwd) {
        //从数据库表t_sreginfo获取该id的正确密码
        Query query = QueryFactory.getQuery();
        String sql = "SELECT pwd FROM t_sreginfo WHERE id=?";
        String cPwd = (String)query.queryValue(sql, new Object[]{id});

        if(pwd.equals(cPwd)){
            msg.setIsLogin("true"); //表示登陆成功
        } else {
            msg.setIsLogin("false");//表示登陆失败
        }
    }

    @Override
    public void register(Integer id, String pwd) {
        //在数据库表t_treginfo中
    }

    @Override
    public void addStudent(Integer id, String name) {

    }

    @Override
    public void inquiry(Integer id) {

    }

    public TOpration() {
        super();
    }
}

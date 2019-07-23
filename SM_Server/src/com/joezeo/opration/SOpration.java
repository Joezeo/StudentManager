package com.joezeo.opration;

import com.joezeo.core.Query;
import com.joezeo.core.QueryFactory;
import com.joezeo.po.T_sreginfo;
import com.joezeo.po.T_student;

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
        //从数据库表t_sreginfo获取该id的正确密码
        Query query = QueryFactory.getQuery();
        String sql = "SELECT pwd FROM t_sreginfo WHERE teaId=?";
        String cPwd = (String)query.queryValue(sql, new Object[]{id});

        if(pwd.equals(cPwd)){
            msg.setIsLogin("true"); //表示登陆成功
        } else {
            msg.setIsLogin("false");//表示登陆失败
        }

        msg.setInquiryContent(null);
        msg.setIsRegister(null);
    }

    @Override
    public void register(Integer id, String pwd, String name) {
        //先从t_student表中获取id、name
        //判断是否与用户输入的信息匹配
        //如匹配则注册成功 否则注册失败
        Query query = QueryFactory.getQuery();
        String sql = "SELECT * FROM t_student WHERE stuId=?";
        T_student ts = (T_student) query.queryRow(T_student.class, sql, new Object[]{id});

        if(ts.getName().equals(name)){
            T_sreginfo sInfo = new T_sreginfo();
            sInfo.setStuId(id);
            sInfo.setPwd(pwd);
            sInfo.setName(name);

            query.insert(sInfo);
            msg.setIsRegister("true");
        } else {
            msg.setIsRegister("false");
        }
    }

    @Override
    public void addStudent(Integer id, String name, Integer score) {
        //学生端不实现此方法
    }

    @Override
    public void inquiry(Integer id) {
        String sql = "SELECT * from t_student WHERE stuId=?";

        Query query = QueryFactory.getQuery();
        T_student value = (T_student)query.queryRow(T_student.class, sql, new Object[]{id});

        msg.setInquiryContent(value.getName() + "：" + value.getScore());
        msg.setIsLogin(null);
        msg.setIsRegister(null);
    }

    public SOpration() {
    }
}

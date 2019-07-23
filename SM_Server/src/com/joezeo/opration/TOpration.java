package com.joezeo.opration;

import com.joezeo.core.Query;
import com.joezeo.core.QueryFactory;
import com.joezeo.po.T_student;
import com.joezeo.po.T_treginfo;

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
        //从数据库表t_treginfo获取该id的正确密码
        Query query = QueryFactory.getQuery();
        String sql = "SELECT pwd FROM t_treginfo WHERE teaId=?";
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
        //在数据库表t_treginfo中
        T_treginfo tInfo = new T_treginfo();

        tInfo.setTeaId(id);
        tInfo.setPwd(pwd);
        tInfo.setName(name);

        Query query = QueryFactory.getQuery();
        query.insert(tInfo);

        msg.setIsLogin(null);
        msg.setInquiryContent(null);

        //暂时不考虑注册失败的情况
        msg.setIsRegister("true");
    }

    @Override
    public void addStudent(Integer id, String name, Integer score) {
        T_student ts = new T_student();
        ts.setStuId(id);
        ts.setName(name);
        ts.setScore(score);

        Query query = QueryFactory.getQuery();
        query.insert(ts);

        msg.setIsRegister(null);
        msg.setIsLogin(null);
        msg.setInquiryContent(null);
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

    public TOpration() {
        super();
    }
}

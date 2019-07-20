package com.joezeo.core;

import com.joezeo.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Query提供一组对数据库的操作服务
 * 采用模板方法设计模式
 */
public abstract class Query {
    /*
    对数据库的操作都会经过：
    Connection conn = DBManager.getConnection();
    PrepareStatement ps = conn.prepareStatement(sql);
    ps.setParams(); 设置参数
    ......具体的处理
    这些步骤 所以使用模板方法设计模式
     */

    /**
     * 方法模板
     *
     * @param clazz    po类class对象
     * @param sql      sql语句
     * @param params   参数列表
     * @param callback 回调对象
     * @return 查询获取的内容
     */
    private Object queryTemplate(Class clazz, String sql, Object[] params, CallBack callback) {
        Object obj = null;

        try {
            Connection conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            JDBCUtils.setParams(ps, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }
}

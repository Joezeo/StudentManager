package com.joezeo.core;

import com.joezeo.bean.ColumnInfo;
import com.joezeo.bean.TableInfo;
import com.joezeo.utils.CloseUtils;
import com.joezeo.utils.JDBCUtils;
import com.joezeo.utils.ReflectionUtils;

import javax.naming.event.ObjectChangeListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Query提供一组对数据库的操作服务
 * 采用模板方法设计模式
 */
public abstract class Query {
    /**
     * 执行一个DML语句
     *
     * @param sql    sql语句
     * @param params 参数列表
     * @return 操作影响的行数
     */
    private int executeDML(String sql, Object[] params) {
        int cnt = 0;

        Connection conn = DBManager.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);

            JDBCUtils.setParams(ps, params);

            cnt = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.close(ps);
            DBManager.closeConn(conn);
        }

        return cnt;
    }

    /**
     * 将一个po对象插入到指定的表中
     *
     * @param obj 需插入的po对象
     * @return 影响行数
     */
    public int insert(Object obj) {
        //object --> INSERT INTO 表名 (字段名，字段名，字段名) VALUES (?,?,?)
        int cnt = 0;

        Class clazz = obj.getClass();

        TableInfo tableInfo = TableContext.getPoClassTableMap().get(clazz);

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(tableInfo.getTname()).append(" (");

        int paramsCount = 0;
        List<Object> params = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            String name = f.getName();
            Object value = ReflectionUtils.invokeGet(name, obj);

            if (value != null) {
                sql.append(name + ",");
                paramsCount++;
                params.add(value);
            }
        }

        sql.setCharAt(sql.length() - 1, ')');
        sql.append(" VALUES (");

        for (int i = 0; i < paramsCount; i++) {
            sql.append("?,");
        }
        sql.setCharAt(sql.length() - 1, ')');

        cnt = executeDML(sql.toString(), params.toArray());

        return cnt;
    }

    /**
     * 通过主键值删除相应记录
     *
     * @param clazz  和po类的Class对象
     * @param priKey 主键
     * @return 执行操作影响的行数
     */
    public int delete(Class clazz, Object priKey) {
        //通过class对象找到TableInfo对象
        TableInfo tableInfo = TableContext.getPoClassTableMap().get(clazz);

        //获取主键信息
        ColumnInfo priKeyInfo = tableInfo.getOnlyPriKey();

        //拼接sql语句
        String sql = "DELETE FROM " + tableInfo.getTname() + " WHERE " + priKeyInfo.getName() + "=?";

        //执行DML语句
        return executeDML(sql, new Object[]{priKey});
    }

    /**
     * 通过字段信息更新响应对象的值
     *
     * @param obj       需更新的对象
     * @param filedName 字段名列表
     * @return 执行更新操作影响的行数
     */
    public int update(Object obj, String[] filedName) {
        //obj,{(字段名),(字段名)} --> UPDATE 表名 SET 字段名=?,字段名=? WHERE id=?
        Class clazz = obj.getClass();
        TableInfo tableInfo = TableContext.getPoClassTableMap().get(clazz);
        ColumnInfo priKey = tableInfo.getOnlyPriKey();
        List<Object> params = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(tableInfo.getTname()).append(" SET ");

        for (String fname : filedName) {
            Object value = ReflectionUtils.invokeGet(fname, obj);
            params.add(value);
            sql.append(fname + "=?,");
        }

        sql.setCharAt(sql.length() - 1, ' ');
        sql.append("WHERE ").append(priKey.getName()).append("=?");
        params.add(ReflectionUtils.invokeGet(priKey.getName(), obj));

        return executeDML(sql.toString(), params.toArray());
    }

    /*
    对数据库的查询操作都会经过：
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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            ps = conn.prepareStatement(sql);
            JDBCUtils.setParams(ps, params);
            rs = ps.executeQuery();

            obj = callback.callBack(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.close(rs, ps);
            DBManager.closeConn(conn);
        }

        return obj;
    }

    /**
     * 查询一行信息
     *
     * @param clazz  该表下对应的po类
     * @param sql    sql语句
     * @param params 参数列表
     * @return po对象
     */
    public Object queryRow(Class clazz, String sql, Object[] params) {
        return queryTemplate(clazz, sql, params, (ResultSet rs) -> {
            Object obj = null;

            try {
                obj = clazz.getDeclaredConstructor(null).newInstance();
                ResultSetMetaData metaData = rs.getMetaData();
                while (rs.next()) {
                    for (int i = 0; i < metaData.getColumnCount(); i++) {
                        String coloumnName = metaData.getColumnName(i + 1);
                        Object value = rs.getObject(i + 1);

                        if (value != null) {
                            ReflectionUtils.invokeSet(coloumnName, value, obj);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            return obj;
        });
    }

    /**
     * 查询一个指定的值
     * @param sql sql语句
     * @param params 参数列表
     * @return 查询到的值
     */
    public Object queryValue(String sql, Object[] params){
        return queryTemplate(null, sql, params, (ResultSet rs)->{
            Object value = null;
            try {
                if(rs.next()){
                    value = rs.getObject(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return value;
        });
    }
}

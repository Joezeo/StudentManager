package com.joezeo.core;

import com.joezeo.xml.XmlSaxParseHome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库管理类
 * 使用连接池来获取Connection对象
 */
public class DBManager {
    //私有化构造器
    private DBManager(){
    }

    /**
     * 数据库配置信息对象
     */
    private static Configuration configuration = null;

    private static ConnectionPool pool = null;

    /**
     * 通过SAX解析 获取Configuration对象
     */
    static {
        configuration = XmlSaxParseHome.getConfiguration();
    }

    /**
     * 根据Configuration配置信息创建Connection连接对象
     * @return 连接对象
     */
    private static Connection createConnection(){
        Connection conn = null;

        try {
            Class.forName(configuration.getDriver());
            conn = DriverManager.getConnection(configuration.getUrl(),
                    configuration.getUser(),
                    configuration.getPwd());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 从数据库连接池中获取Connection对象
     * @return 连接对象
     */
    public static Connection getConnection(){
        /*
         * 不能将pool的初始化放入放入static初始化块中
         * 否则DBManager 和 ConnectionPool会相互依赖 出现错误
         */
        if(pool == null){
            pool = new ConnectionPool();
        }

        return pool.getConnection();
    }

    /**
     * 空方法：用于加载DBManager类，获取static属性信息
     */
    public static void loadDBManager(){
    }
}

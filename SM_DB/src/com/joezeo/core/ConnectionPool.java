package com.joezeo.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接池类：用于管理数据库的连接对象提高效率
 * 连接池内部，用一个List来存放连接对象，最少存放MIN_CAPACITY个，最多为MAX_CAPACITY
 * 使用静态初始化块来初始化连接池
 */
public class ConnectionPool {
    /**
     * 连接池内部最少存放的连接对象个数
     */
    private final int MIN_CAPACITY = 10;

    /**
     * 连接池内部最少存放的连接对象个数
     */
    private final int MAX_CAPACITY = 50;

    /**
     * 连接池：用List实现
     */
    private List<Connection> pool;

    /**
     * 初始化数据库连接池
     */
    private void initPool() {
        pool = new ArrayList<>();

        while (pool.size() < MIN_CAPACITY) {
            pool.add(DBManager.createConnection());
        }
    }

    /**
     * 构造函数：初始化数据库连接池
     */
    public ConnectionPool() {
        initPool();
    }

    /**
     * 从连接池中获取一个connection对象
     * 并将其从连接池中删除
     *
     * @return
     */
    public synchronized Connection getConnection() {
        /*
        如果连接池空了调用addConnection方法
        根据情况添加Connection
         */
        if (pool.size() == 0) {
            addConnection();
        }

        int index = pool.size() - 1;
        Connection conn = pool.get(index);
        pool.remove(index);
        return conn;
    }

    /**
     * 关闭连接对象：
     * 将连接对象重新放入连接池中
     * 如果连接池中的连接对象数量已经超过最大容量限制时才真正关闭连接
     * @param conn
     */
    public synchronized void close(Connection conn) {
        /*
        如果连接池中的连接对象数量已经超过最大容量限制时真正关闭连接
         */
        if (pool.size() > MAX_CAPACITY) {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /*
        将连接对象重新放入连接池中
         */
        else {
            pool.add(conn);
        }
    }

    /**
     * 添加Connecion
     */
    private void addConnection() {
        while (pool.size() < MIN_CAPACITY) {
            pool.add(DBManager.getConnection());
        }
    }
}

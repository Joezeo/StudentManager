package com.joezeo.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据库操作的工具类
 */
public class JDBCUtils {
    /**
     * 给PrepareStatement设置参数
     *
     * @param ps     需要设置参数的PrepareStatement对象
     * @param params 参数列表
     */
    public static void setParams(PreparedStatement ps, Object[] params) {
        for (int i = 0; i < params.length; i++) {
            try {
                ps.setObject(i + 1, params[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

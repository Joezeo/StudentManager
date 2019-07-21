package com.joezeo.core;


import com.joezeo.bean.ColumnInfo;
import com.joezeo.bean.TableInfo;
import com.joezeo.utils.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 获取管理数据库所有表结构和类结构的关系
 * 可以根据表结构生成类结构
 */
public class TableContext {
    /**
     * key：表名
     * value：表信息
     * 存储数据库中所有的表格信息
     */
    private static Map<String, TableInfo> tables = new HashMap<>();

    /**
     * key：po的class对象
     * value：表信息
     */
    private static Map<Class, TableInfo> poClassTableMap = new HashMap<>();

    private TableContext() {
    }

    public static Map<String, TableInfo> getTableInfos() {
        return tables;
    }

    public static Map<Class, TableInfo> getPoClassTableMap() {
        return poClassTableMap;
    }

    /**
     * 将PO class对象加载 放入poClassTableMap中存储
     */
    private static void loadPOClass() {
        for (TableInfo ti : tables.values()) {
            try {
                Class clazz = Class.forName(DBManager.getConfiguration().getPoPackage()
                        + "."
                        + StringUtils.first2UpperCase(ti.getTname()));

                poClassTableMap.put(clazz, ti);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化获取数据库所有表格信息 存入tables中
     * 同时创建、更新与表结构对应的类文件
     * 将PO包下的 class对象加载 放入poClassTableMap中存储
     */
    static {
        try {
            //初始化获得表信息
            Connection conn = DBManager.getConnection();
            DatabaseMetaData dbmd = conn.getMetaData();

            ResultSet tableRet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});

            while (tableRet.next()) {
                String tableName = (String) tableRet.getObject("TABLE_NAME");

                TableInfo ti = new TableInfo(tableName, new HashMap<>(), new ArrayList<>());

                tables.put(tableName, ti);

                //获取所有字段信息
                ResultSet columnSet = dbmd.getColumns(null, "%", tableName, "%");
                while (columnSet.next()) {
                    ColumnInfo ci = new ColumnInfo(columnSet.getString("COLUMN_NAME"),
                            columnSet.getString("TYPE_NAME"), 0);

                    ti.getColumns().put(columnSet.getString("COLUMN_NAME"), ci);
                }

                //获取所有主键信息
                ResultSet prikeySet = dbmd.getPrimaryKeys(null, "%", tableName);
                while (prikeySet.next()) {
                    ColumnInfo ci2 = ti.getColumns().get(prikeySet.getObject("COLUMN_NAME"));
                    ci2.setKeytype(1); //设为主键
                    ti.getPriKey().add(ci2);
                }

                //取唯一主键 方便使用
                if (ti.getPriKey().size() > 0) {
                    ti.setOnlyPriKey(ti.getPriKey().get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadPOClass();
    }

    /**
     * 对外提供一个空方法 用于每次启动框架时都执行此TableContext下的static块
     * 创建、更新与表结构对应的类文件
     */
    public static void loadTableContext(){

    }

}

package com.joezeo.bean;

import java.util.List;
import java.util.Map;

/**
 * 管理一张表的信息
 */
public class TableInfo {
    /**
     * 表名
     */
    private String tname;

    /**
     * 所有字段信息
     * key：字段名
     * value：字段信息
     */
    private Map<String, ColumnInfo> columns;

    /**
     * 唯一主键
     */
    private ColumnInfo onlyPriKey;

    /**
     * 如果是联合主键则在这里储存
     */
    private List<ColumnInfo> priKey;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }

    public ColumnInfo getOnlyPriKey() {
        return onlyPriKey;
    }

    public void setOnlyPriKey(ColumnInfo onlyPriKey) {
        this.onlyPriKey = onlyPriKey;
    }

    public List<ColumnInfo> getPriKey() {
        return priKey;
    }

    public void setPriKey(List<ColumnInfo> priKey) {
        this.priKey = priKey;
    }

    public TableInfo(String tname, Map<String, ColumnInfo> columns, ColumnInfo onlyPriKey) {
        this.tname = tname;
        this.columns = columns;
        this.onlyPriKey = onlyPriKey;
    }

    public TableInfo(String tname, Map<String, ColumnInfo> columns, List<ColumnInfo> priKey) {
        this.tname = tname;
        this.columns = columns;
        this.priKey = priKey;
    }

    public TableInfo() {
    }
}

package com.joezeo.bean;

/**
 * 表中的一个字段的信息
 */
public class ColumnInfo {
    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 键类型（0-一般键 1-主键 2-外键）
     */
    private int keytype;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getKeytype() {
        return keytype;
    }

    public void setKeytype(int keytype) {
        this.keytype = keytype;
    }

    public ColumnInfo(String name, String dataType, int keytype) {
        this.name = name;
        this.dataType = dataType;
        this.keytype = keytype;
    }

    public ColumnInfo() {
    }
}

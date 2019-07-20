package com.joezeo.core;

/**
 * 数据库配置信息
 */
public class Configuration {
    /**
     * 使用驱动类型
     */
    private String driver;

    /**
     * 数据库url
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String user;

    /**
     * 数据库密码
     */
    private String pwd;

    /**
     * 使用的数据库类型
     */
    private String usingDB;

    /**
     * 使用数据库类型的Query子类
     */
    private String queryClass;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsingDB() {
        return usingDB;
    }

    public void setUsingDB(String usingDB) {
        this.usingDB = usingDB;
    }

    public String getQueryClass() {
        return queryClass;
    }

    public void setQueryClass(String queryClass) {
        this.queryClass = queryClass;
    }

    public Configuration(String driver, String url, String user, String pwd, String usingDB, String queryClass) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        this.usingDB = usingDB;
        this.queryClass = queryClass;
    }

    public Configuration() {
    }
}

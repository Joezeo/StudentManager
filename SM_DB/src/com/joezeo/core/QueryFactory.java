package com.joezeo.core;

/**
 * Query工厂
 * 对外提供Query对象
 */
public class QueryFactory {
    //私有化构造器
    private QueryFactory() {

    }

    static {
        //加载DBManager 获取配置信息...
        DBManager.loadDBManager();
        //获取表上下文 获取数据库表与po类的信息
        TableContext.loadTableContext();
    }

    /**
     * 静态内部类 只有在真正调用getQuery方法时才会真正初始化这个类
     */
    private static class Inner {
        private static final Query query = new MysqlQuery();
    }

    /**
     * 获取Query对象
     *
     * @return Query对象
     */
    public static Query getQuery() {
        return Inner.query;
    }
}

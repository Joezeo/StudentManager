package com.joezeo.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtils {
    /**
     * 通过反射调用po对象的set方法
     *
     * @param columnName 列名
     * @param value      值
     * @param obj        po对象
     */
    public static void invokeSet(String columnName, Object value, Object obj) {
        try {
            Class clazz = obj.getClass();
            Method m = clazz.getDeclaredMethod("set" + StringUtils.first2UpperCase(columnName), value.getClass());
            m.invoke(obj, value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射调用po对象的get方法
     *
     * @param columnName 列名
     * @param obj        po对象
     * @return 执行get方法后获得的值
     */
    public static Object invokeGet(String columnName, Object obj) {
        Class clazz = obj.getClass();
        Object value = null;

        try {
            Method m = clazz.getDeclaredMethod("get" + StringUtils.first2UpperCase(columnName));
            value = m.invoke(obj, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return value;
    }
}

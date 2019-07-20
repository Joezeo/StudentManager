package com.joezeo.utils;

import com.joezeo.message.RequestMessage;
import com.joezeo.opration.Opration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtils {
    /**
     * 工具类 私有化构造器
     */
    private ReflectionUtils() {

    }

    /**
     * 根据客户端角色类型获取class对象
     *
     * @param character 客户端角色类型
     * @return 相应Opration class对象
     */
    public static Class getClassFromCharacter(String character) {
        Class<Opration> clazz = null;
        if (character.equals("teacher")) {
            try {
                clazz = (Class<Opration>) Class.forName("com.joezeo.opration.TOpration");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("教师端操作类TOpration没有找到~");
            }
        } else if (character.equals("student")) {
            try {
                clazz = (Class<Opration>) Class.forName("com.joezeo.opration.SOpration");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("教师端操作类SOpration没有找到~");
            }
        } else {
            clazz = null;
        }

        return clazz;
    }

    /**
     * 根据class对象 创建相应Opration对象
     *
     * @param clazz 泛型为Opration的class对象
     * @return Opration对象
     */
    private static Opration createOpration(Class<Opration> clazz) {
        if (clazz == null) {
            return null;
        }

        Opration opration = null;

        try {
            opration = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建Opration对象失败~~");
        }

        return opration;
    }

    /**
     * 执行methodName相应的方法
     *
     * @param methodName 方法名
     * @param clazz      Class对象
     * @param msg        请求消息
     * @return Opration对象
     */
    public static Opration invokeOpration(String methodName, Class<Opration> clazz, RequestMessage msg) {
        Opration opration = createOpration(clazz);

        /**
         * 这里还有addStudent方法还没有写
         */
        if (methodName.equals("login")
                || methodName.equals("register")) {
            try {
                Method m = clazz.getDeclaredMethod(methodName, new Class[]{Integer.class, String.class});
                m.invoke(opration, msg.getId(), msg.getPwd());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                System.out.println("没有找到相应的Opration方法：" + methodName);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (methodName.equals("addStudent")) {
            try {
                Method m = clazz.getDeclaredMethod(methodName, new Class[]{Integer.class, String.class});
                m.invoke(opration, msg.getId(), msg.getName());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                System.out.println("没有找到相应的Opration方法：" + methodName);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (methodName.equals("inquiry")) {
            try {
                Method m = clazz.getDeclaredMethod(methodName, new Class[]{Integer.class});
                m.invoke(opration, msg.getId());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                System.out.println("没有找到相应的Opration方法：" + methodName);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return opration;
    }
}

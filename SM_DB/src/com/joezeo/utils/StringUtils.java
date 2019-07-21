package com.joezeo.utils;

public class StringUtils {
    /**
     * 将字符串首字母转为大写
     * @param str
     * @return
     */
    public static String first2UpperCase(String str){
        return str.toUpperCase().substring(0,1)+str.substring(1);
    }
}

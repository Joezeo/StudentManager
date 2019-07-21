package com.joezeo.core;

import java.sql.ResultSet;

/**
 * 用于方法回调
 */
public interface CallBack {
    Object callBack(ResultSet rs);
}

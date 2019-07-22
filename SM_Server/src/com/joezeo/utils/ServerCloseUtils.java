package com.joezeo.utils;

import java.io.Closeable;
import java.io.IOException;

public class ServerCloseUtils {
    /**
     * 私有化构造器
     */
    private ServerCloseUtils() {

    }

    public static void close(Closeable... cs) {
        for (Closeable c : cs) {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

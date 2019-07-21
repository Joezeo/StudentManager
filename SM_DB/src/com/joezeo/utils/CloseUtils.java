package com.joezeo.utils;

public class CloseUtils {
    public static void close(AutoCloseable... acs) {
        for (AutoCloseable ac : acs) {
            try {
                if (ac != null) {
                    ac.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

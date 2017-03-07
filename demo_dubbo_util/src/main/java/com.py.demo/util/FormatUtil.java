package com.py.demo.util;

/**
 * 特殊类型值转换
 * Created by zczhao on 16/4/15.
 */
public class FormatUtil {


    public static String formatNullString(String str) {
        if (null == str) {
            return "";
        } else {
            return str.trim();
        }
    }


    public static int formatNullInteger(Integer number) {
        if (null == number) {
            return -1;
        } else {
            return number;
        }
    }

    public static long formatNullLong(Long number) {
        if (null == number) {
            return -1L;
        } else {
            return number;
        }
    }

}

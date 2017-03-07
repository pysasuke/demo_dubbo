package com.py.demo.util;


import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zczhao on 16/4/18.
 * 验证手机号和图片格式
 */
public class CheckValueUtil {

    /**
     * 校验手机号码
     *
     * @param phoneNum
     * @return
     */
    public static boolean checkPhoneNumber(String phoneNum) {
        if (StringUtils.isBlank(phoneNum)
                || !phoneNum.matches("1[34578]\\d{9}")) {
            return false;
        }
        return true;
    }

    /**
     * 校验图片格式
     *
     * @param url
     * @return
     */
    public static boolean checkImage(String url) {//TODO 判断是否以jpg，jpeg等结尾

        if (url == null) {
            return false;
        }
        String regEx = ".+(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png)$";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(url);
        return matcher.matches();
    }

}

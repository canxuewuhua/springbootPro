package com.example.demo.util;

/**
 *  工具类 拼接Url 带参数的拼接
 */
public class StringConcatUtil {

    public static final String httpUrl = "/html/activity/flow-register-d.html?vendor=%s&fromSource=%s&mobile=%s";

    public static String concatHttpUrl(){
        return String.format(httpUrl,  "bbb", "ccc", "17310353508");
    }
}

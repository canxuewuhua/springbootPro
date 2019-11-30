package com.example.demo.util;

/**
 *  工具类 拼接Url 带参数的拼接
 */
public class StringConcatUtil {

    public static final String httpUrl = "/h5/activity/20190521YKD-YD/index.html?subChannel=%s&inChannel=%s&recommended=%s&token=%s";

    public static String concatHttpUrl(){
        return String.format(httpUrl,  "207", "207" + "jinshan","ykdjinshan","LD_8V3E5RSZ4VMQP9P38K8T5YYENSX3R683");
    }

    public static void main(String[] args) {
        System.out.println(concatHttpUrl());
    }
}

package com.example.demo.util;

import org.springframework.util.StringUtils;

public class DesensitizationUtils {

    /**
     * 手机号脱敏
     *
     * @param mobile
     * @return
     */
    public static String mobileEncrypt(String mobile) {
        if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 身份证号脱敏
     *
     * @param id
     * @return
     */
    public static String idNumberEncrypt(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

    /**
     * 身份证号脱敏
     * @param id
     * @param beforeIndex 号码前几位显示
     * @param afterIndex 号码后几位显示
     * @return
     */
    public static String idNumberEncryptByIndex(String id, int beforeIndex, int afterIndex) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.replaceAll("(?<=\\w{"+ beforeIndex +"})\\w(?=\\w{"+ afterIndex +"})", "*");
    }

    /**
     * 姓名脱敏
     *
     * @param name
     * @return
     */
    public static String nameEncrypt(String name) {
        if (StringUtils.isEmpty(name) || (name.length() < 2)) {
            return name;
        } else if (name.length() == 2) {
            return name.replaceAll(".(?=.)", "*");
        }
        return name.replaceAll("(?<=.).(?=.)", "*");
    }

    /**
     * 姓名脱敏（隐藏最后一个字）
     *
     * @param name
     * @return
     */
    public static String nameEncrypt1(String name) {
        if (StringUtils.isEmpty(name) || (name.length() < 2)) {
            return name;
        }
        return name.substring(0, name.length() -1) + "*";
    }

    /**
     * 姓名脱敏（只显示最后一个字）
     *
     * @param name
     * @return
     */
    public static String nameEncrypt2(String name) {
        if (StringUtils.isEmpty(name) || (name.length() < 2)) {
            return name;
        }
        return name.replaceAll(".(?=.)", "*");
    }

    /**
     * 银行卡号码脱敏
     *
     * @param cardNo
     * @return
     */
    public static String cardNoEncrypt(String cardNo) {
        if (StringUtils.isEmpty(cardNo) || (cardNo.length() < 8)) {
            return cardNo;
        }
        return cardNo.replaceAll("(?<=\\w{4})\\w(?=\\w{4})", "*");
    }

    /**
     * 地址脱敏 todo 规则有待确认
     *
     * @param address
     * @return
     */
    public static String addressEncrypt(String address) {
        if (StringUtils.isEmpty(address) || (address.length() < 5)) {
            return address;
        }
        return address.replaceAll("(?<=.{4}).", "*");
    }

    /**
     * 通用脱敏
     *
     * @param text
     * @return
     */
    public static String commonEncrypt(String text) {
        if (StringUtils.isEmpty(text) || text.length() < 2) {
            return text;
        }
        return nameEncrypt(text);
    }
}

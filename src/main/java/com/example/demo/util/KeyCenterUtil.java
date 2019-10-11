package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
public class KeyCenterUtil {
    private static Logger logger = LoggerFactory.getLogger(KeyCenterUtil.class);

    /**
     * 调用keycenter加密第三方数据
     *
     * @param userId
     * @param clearText
     * @return
     */
    public static String encryptThird(String userId, String clearText) {
        String encryptText = null;
        return encryptText;
    }

    /**
     * 调用keycenter解密第三方数据
     *
     * @param userId
     * @param encryptText
     * @return
     */
    public static String decryptThird(String userId, String encryptText) {
        String clearText = null;
        return clearText;
    }

    /**
     * 调用keycenter获取加密密钥
     *
     * @param type
     * @return
     */
    public static String getEncryptKey(String type) {
        String encryptKey = null;
        return encryptKey;
    }

    /**
     * 调用keycenter获取第三方加密密钥，只支持AES
     *
     * @param userId
     * @return
     */
    public static String getThirdEncryptKey(String userId) {
        String thirdEncryptKey = "e2709ad12465434eadad46f6c768d273";
        return thirdEncryptKey;
    }

    public static String getMD5Encrypt(String string) {
        if (string == null || string.trim().length() < 1) {
            return null;
        }
        String result = null;
        // 用来将字节转换成 16 进制表示的字符
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes("UTF-8"));
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            result = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

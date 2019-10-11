package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * import org.apache.commons.codec.binary.Base64;
 * import javax.crypto.Cipher;
 * import javax.crypto.spec.SecretKeySpec;
 * Created by Administrator on 2018/5/24.
 * <p>
 * AES 256
 */
@Slf4j
public class AESUtils {


    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

    /**
     * 加密
     *
     * @param content
     * @param strKey
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String strKey) throws Exception {
        log.debug("AES加密原文={}", content);
        String csn = Charset.defaultCharset().name();
        log.debug("defaultCharset={}", csn);
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
        return Base64.encodeBase64String(encrypted);
    }

    /**
     * 解密
     *
     * @param strKey
     * @param content
     * @return
     * @throws Exception
     */
    public static String decrypt(String content, String strKey) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] original = cipher.doFinal(Base64.decodeBase64(content));
        return new String(original, "utf-8");//通过Base64转码返回
    }

    private static SecretKeySpec getKey(String strKey) {
        byte[] arrBTmp = new byte[0];
        try {
            arrBTmp = strKey.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("编码异常{}", e.getMessage(), e);
        }
        byte[] arrB = new byte[32]; // 创建一个空的32位字节数组（默认值为0）

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(arrB, KEY_ALGORITHM);

        return skeySpec;
    }
}

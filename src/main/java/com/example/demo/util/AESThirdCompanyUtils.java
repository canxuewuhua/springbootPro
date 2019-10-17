package com.example.demo.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 第三方公司ES加解密方法，与我们定义的AES加解密算法不同
 */
public class AESThirdCompanyUtils {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
    private static final String CHARSET_NAME = "utf-8";

    public static String encrypt(String content, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        byte[] byteContent = content.getBytes(CHARSET_NAME);
        cipher.init(1, getSecretKey(key));
        byte[] result = cipher.doFinal(byteContent);
        return Base64.encodeBase64String(result);
    }

    public static String decrypt(String content, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(2, getSecretKey(key));
        byte[] result = cipher.doFinal(Base64.decodeBase64(content));
        return new String(result, CHARSET_NAME);
    }

    private static SecretKeySpec getSecretKey(String key) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
        secureRandom.setSeed(key.getBytes());
        kg.init(128, secureRandom);
        SecretKey secretKey = kg.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    }

    public static final String randomSecretKey() {
        return RandomStringUtils.randomNumeric(32);
    }
}

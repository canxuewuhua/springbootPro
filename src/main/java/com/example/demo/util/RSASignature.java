package com.example.demo.util;

import com.example.demo.common.CodeMsg;
import com.example.demo.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class RSASignature {

    private static Logger logger = LoggerFactory.getLogger(RSASignature.class);

    public static final String KEY_ALGORITHM = "RSA";
    /**
     * 加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式
     */
    public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";

    public static final String SIGNATURE_ALGORITHM = "SHA256WithRSA";

    /**
     * 字符集编码采用UTF-8
     */
    public static final String ENCODING = "utf-8";
    public static final String X509 = "X.509";

    /**
     * 获取私钥
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 获取公钥
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * RSA私钥签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥
     * @return 签名值
     */
    public static String signByPrivateKey(String content, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(content.getBytes(ENCODING));
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64(signed), ENCODING);
        } catch (Exception e) {
            logger.error("sign error", e);
            throw new ServiceException(CodeMsg.SYSTEM_VERIFY_SIGN_FAIL);
        }
    }

    public static boolean verifySignByPublicKey(String content, String sign,
                                                String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decodeBase64(publicKey.getBytes(ENCODING));
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(ENCODING));

            return signature.verify(Base64.decodeBase64(sign.getBytes(ENCODING)));

        } catch (Exception e) {
            logger.error("sign error", e);
            throw new ServiceException(CodeMsg.SYSTEM_VERIFY_SIGN_FAIL);
        }
    }


    public static String encryptByPublicKey(String plainText, String publicKey) {
        try {
            PublicKey pubKey = getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] enBytes = cipher.doFinal(plainText.getBytes(ENCODING));
            return new String(Base64.encodeBase64(enBytes), ENCODING);
        } catch (Exception e) {
            logger.error("encrypt error", e);
            throw new RuntimeException("encrypt error", e);
        }
    }

    public static String decryptByPrivateKey(String enStr, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] deBytes = cipher.doFinal(Base64.decodeBase64(enStr.getBytes(ENCODING)));
            return new String(deBytes);
        } catch (Exception e) {
            logger.error("sign error", e);
            throw new ServiceException(CodeMsg.SYSTEM_VERIFY_SIGN_FAIL);
        }
    }

    /**
     * 对签名转换升序排序
     *
     * @param params
     * @return
     */
    public static String getSignContent(Map<String, String> params) {
        Map<String, String> keyValueMap = new TreeMap<String, String>();

        for (Map.Entry entry : params.entrySet()){
            String key = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());
            if (!"sign".equals(key) && !"signType".equals(key) && StringUtils.isNotBlank(value)) {
                keyValueMap.put(key, params.get(key));
            }
        }
        logger.info("参加签名的参数列表：{}", Joiner.on("&").withKeyValueSeparator("=").join(keyValueMap));
        return Joiner.on("&").withKeyValueSeparator("=").join(keyValueMap);
    }

    /**
     * signType也参加签名
     *
     * @param params
     * @param joinNull true 空值加入签名，false 空值不加入签名
     * @return
     */
    public static String getSignContentWithSignType(Map<String, String> params, Boolean joinNull) {

        TreeMap<String, String> sortMap = new TreeMap<>(params);

        if (sortMap.containsKey("sign")) {
            sortMap.remove("sign");
        }
        StringBuilder sb = new StringBuilder();

        for (Map.Entry entry : sortMap.entrySet()) {
            String value = String.valueOf(entry.getValue());
            if (StringUtils.isBlank(value) && !joinNull){
                continue;
            }
            sb.append(entry.getKey() + "=" + value);
            sb.append("&");
        }
        String signContent = sb.toString();
        if (signContent.endsWith("&")) {
            signContent = StringUtils.substringBeforeLast(signContent, "&");
        }
        return signContent;
    }

    /**
     * 对签名转换升序排序
     *
     * @param params
     * @return
     */
    public static String getSignContent(List<NameValuePair> params) {
        Map<String, String> keyValueMap = new TreeMap<String, String>();
        for (NameValuePair nameValuePair : params) {
            if (!"sign".equals(nameValuePair.getName()) && !"signType".equals(nameValuePair.getName()) && StringUtils.isNotBlank(nameValuePair.getValue())) {
                keyValueMap.put(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        return Joiner.on("&").withKeyValueSeparator("=").join(keyValueMap);
    }

    public static String getSignContent(Object paramObj) {
        ObjectMapper m = new ObjectMapper();
        Map<String, String> mappedObject = m.convertValue(paramObj, Map.class);

        return getSignContent(mappedObject);
    }

    public static String getSignContentWithSignType(Object paramObj, Boolean joinNull) {
        ObjectMapper m = new ObjectMapper();
        Map<String, String> mappedObject = m.convertValue(paramObj, Map.class);

        return getSignContentWithSignType(mappedObject, joinNull);
    }

    /**
     * 函数功能 :RSA非对称加密，用于对16位的AES秘钥进行加密,算法为RSA/ECB/PKCS1Padding
     * 参数：TobeEncryptMsg待加密的字符串，如16位AES秘钥
     */

    public static String rsaEncode(byte[] tobeEncryptMsg, PublicKey publicKey) {
        //获取公钥
        Cipher instance;
        try {
            instance = Cipher.getInstance(RSA_ALGORITHM);
            instance.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = instance.doFinal(tobeEncryptMsg);
            return new String(Base64.encodeBase64(bytes), ENCODING);
        } catch (Exception e) {
            throw new RuntimeException("RSA加密失败", e);
        }

    }

    /**
     * * 函数功能 :RSA非对称解密，用于对16位的AES秘钥进行解密,算法为RSA/ECB/PKCS1Padding
     * 参数：TobeEncryptMsg待加密的字符串，如16位AES秘钥
     **/

    public static byte[] rsaDecode(String tobeDecryptMsg, PrivateKey privateKey) {
        Cipher instance;
        byte[] result = null;
        try {
            byte[] tempBtypes = Base64.decodeBase64(tobeDecryptMsg.getBytes(ENCODING));
            instance = Cipher.getInstance(privateKey.getAlgorithm());
            instance.init(Cipher.DECRYPT_MODE, privateKey);
            result = instance.doFinal(tempBtypes);
        } catch (Exception e) {
            throw new RuntimeException("RSA解密失败", e);

        }
        return result;
    }
}

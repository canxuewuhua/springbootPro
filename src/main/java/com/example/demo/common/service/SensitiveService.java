package com.example.demo.common.service;

import com.example.demo.knowledge.zidingyi_interface.annotation.Encryption;
import com.example.demo.util.AESUtils;
import com.example.demo.util.DesensitizationUtils;
import com.example.demo.util.KeyCenterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

@Service
@Slf4j
public class SensitiveService {

    private static final String CUSTOMER_ID = "customerId";
    private static final String CUSTOMER_CODE = "customerCode";


    /**
     * 敏感信息对象加密
     *
     * @param obj
     * @param isByCustomerCode true:使用客户秘钥加密，false：使用商户秘钥加密
     * @return
     * @throws Exception
     */
    public Object encryptObject(Object obj, boolean isByCustomerCode) throws Exception {
        Long start = System.currentTimeMillis();
        if (isSkipCheckField(obj)) {
            return obj;
        }
        // 获取该对象注解中配置的加密字段名称
        Set<String> encryptFields = this.getEncryptFieldNames(obj);
        if (encryptFields.isEmpty()) {
            log.debug("该对象没有加密字段,className={}", obj.getClass());
            return obj;
        }
        log.debug("该对象={}的加密字段：{}", obj.getClass(), encryptFields);
        // 获取该对象所有的加密字段值以及customerCode和customerId
        encryptFields.add(CUSTOMER_CODE);
        encryptFields.add(CUSTOMER_ID);
        Map<String, String> fieldValueMap = this.getEncryptFieldValues(obj, encryptFields);
        String customerId = fieldValueMap.remove(CUSTOMER_ID);
        String customerCode = fieldValueMap.remove(CUSTOMER_CODE);
        encryptFields.remove(CUSTOMER_CODE);
        encryptFields.remove(CUSTOMER_ID);

        String encryptKey = null;
        if (isByCustomerCode) {
            // 如果客户号为空，但是客户ID不为空，则根据客户ID查询客户号
            if (StringUtils.isBlank(customerCode) && StringUtils.isNotBlank(customerId)) {
                log.error("客户号和客户ID都不能为空！");
            }

            // 如果客户号为空，但是含有加密数据，则抛出异常不能加密
            if (StringUtils.isBlank(customerCode)) {
                if (fieldValueMap.isEmpty()) {
                    return obj;
                }
                log.error("对象含有敏感数据但是客户编码为空，不能加密，className={}, 需要加密的数据={}",
                        obj.getClass().getName(), this.desensitizationMapValues(fieldValueMap));
                //throw new ServiceException(CodeMsg.SYSTEM_SENSITIVE_ENCRYPT_CUSTOMER_CODE_IS_NULL);
            }

            // 获取该用户的秘钥
            encryptKey = KeyCenterUtil.getThirdEncryptKey(customerCode);
        } else {
            encryptKey = KeyCenterUtil.getEncryptKey("AES");
        }
        if (StringUtils.isBlank(encryptKey)) {
            log.error("获取加密秘钥失败, customerCode={},isByCustomerCode={}", customerCode, isByCustomerCode);
            //throw new ServiceException(CodeMsg.SYSTEM_SENSITIVE_GET_THIRD_ENCRYPT_KEY_FAIL);
        }


        // 给字段加密并将对象的明文替换为密文
        Class<?> clazz = obj.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String fieldName = field.getName();
                String fieldValue = fieldValueMap.get(fieldName);
                if (encryptFields.contains(fieldName) && StringUtils.isNotBlank(fieldValue)) {
                    try {
                        field.set(obj, encryptByKey(fieldValue, encryptKey));
                    } catch (Exception e) {
                        log.error("敏感信息加密失败, customerCode={},isByCustomerCode={}, {}={}", customerCode, isByCustomerCode, fieldName, DesensitizationUtils.nameEncrypt(fieldValue), e);
                        //throw new ServiceException(CodeMsg.SYSTEM_SENSITIVE_DECRYPT_FAIL);
                    }
                }
            }
        }

        log.debug("对象敏感信息 {} 加密耗时 {} 毫秒", encryptFields, (System.currentTimeMillis() - start));
        return obj;
    }

    /**
     * 敏感信息对象解密
     *
     * @param obj
     * @param isByCustomerCode true:使用客户秘钥解密 false：使用商户秘钥解密
     * @return
     * @throws Exception
     */
    public Object decryptObject(Object obj, boolean isByCustomerCode) throws Exception {
        Long start = System.currentTimeMillis();
        if (isSkipCheckField(obj)) {
            return obj;
        }
        // 获取该对象注解中配置的加密字段名称
        Set<String> encryptFields = this.getEncryptFieldNames(obj);
        if (encryptFields.isEmpty()) {
            log.debug("该对象没有解密字段,className={}", obj.getClass());
            return obj;
        }
        log.debug("该对象={}的解密字段：{}", obj.getClass(), encryptFields);
        // 获取该对象所有的加密字段值以及customerCode和customerId
        encryptFields.add(CUSTOMER_CODE);
        encryptFields.add(CUSTOMER_ID);
        Map<String, String> fieldValueMap = this.getEncryptFieldValues(obj, encryptFields);
        String customerId = fieldValueMap.remove(CUSTOMER_ID);
        String customerCode = fieldValueMap.remove(CUSTOMER_CODE);
        encryptFields.remove(CUSTOMER_CODE);
        encryptFields.remove(CUSTOMER_ID);

        // 获取秘钥
        String encryptKey = null;
        if (isByCustomerCode) {
            // 如果客户号为空，但是客户ID不为空，则根据客户ID查询客户号
            if (StringUtils.isBlank(customerCode) && StringUtils.isNotBlank(customerId)) {
                log.error("客户号为空，客户ID不为空！");
            }

            // 如果客户号为空，但是含有加密数据，则抛出异常不能加密
            if (StringUtils.isBlank(customerCode)) {
                if (fieldValueMap.isEmpty()) {
                    return obj;
                }
                log.error("对象含有敏感数据但是客户编码为空，不能解密，className={}, 需要解密的数据={}",
                        obj.getClass().getName(), fieldValueMap);
                //throw new ServiceException(CodeMsg.SYSTEM_SENSITIVE_ENCRYPT_CUSTOMER_CODE_IS_NULL);
            }
            // 获取该用户的秘钥
            encryptKey = KeyCenterUtil.getThirdEncryptKey(customerCode);
        } else {
            encryptKey = KeyCenterUtil.getEncryptKey("AES");
        }
        if (StringUtils.isBlank(encryptKey)) {
            log.error("获取加密秘钥失败, customerCode={},isByCustomerCode={}", customerCode, isByCustomerCode);
            //throw new ServiceException(CodeMsg.SYSTEM_SENSITIVE_GET_THIRD_ENCRYPT_KEY_FAIL);
        }

        // 给字段加密并将对象的明文替换为密文
        Class<?> clazz = obj.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String fieldName = field.getName();
                String fieldValue = fieldValueMap.get(fieldName);
                if (encryptFields.contains(fieldName) && StringUtils.isNotBlank(fieldValue)) {
                    try {
                        field.set(obj, decryptByKey(fieldValue, encryptKey));
                    } catch (Exception e) {
                        log.error("敏感信息解密失败, customerCode={},isByCustomerCode={}, {}={}", customerCode, isByCustomerCode, fieldName, DesensitizationUtils.nameEncrypt(fieldValue), e);
                        //throw new ServiceException(CodeMsg.SYSTEM_SENSITIVE_DECRYPT_FAIL);
                    }
                }
            }
        }

        log.debug("对象敏感信息 {} 解密耗时 {} 毫秒", encryptFields, (System.currentTimeMillis() - start));
        return obj;
    }

    /**
     * 根据客户编码调用keycenter加密字符串
     *
     * @param text
     * @param customerCode
     * @return
     */
    public String encryptStringByCustomerCode(String text, String customerCode) {

        if (StringUtils.isBlank(text)) {
            log.error("加密文本为空，不能加密，customerCode={}, text={}", customerCode, text);
            return text;
        }

        if (StringUtils.isBlank(customerCode)) {
            log.error("客户编码为空，不能加密，customerCode={}, text={}", customerCode, DesensitizationUtils.commonEncrypt(text));
            //hrow new ServiceException(CodeMsg.SYSTEM_SENSITIVE_ENCRYPT_CUSTOMER_CODE_IS_NULL);
        }

        String result = KeyCenterUtil.encryptThird(customerCode, text);
        if (StringUtils.isBlank(result)) {
            log.error("敏感信息加密失败, customerCode={}, text={}", customerCode, DesensitizationUtils.commonEncrypt(text));
            //throw new ServiceException(CodeMsg.SYSTEM_SENSITIVE_ENCRYPT_FAIL);
        }
        return result;
    }

    /**
     * 根据客户编码调用keycenter解密字符串
     *
     * @param text
     * @param customerCode
     * @return
     */
    public String decryptStringByCustomerCode(String text, String customerCode) {

        if (StringUtils.isBlank(text)) {
            log.error("解密文本为空，不能解密，customerCode={}, text={}", customerCode, text);
            return text;
        }

        if (StringUtils.isBlank(customerCode)) {
            log.error("客户编码为空，不能解密，customerCode={}, text={}", customerCode, text);
            //throw new ServiceException(CodeMsg.SYSTEM_SENSITIVE_DECRYPT_CUSTOMER_CODE_IS_NULL);
        }

        String result = KeyCenterUtil.decryptThird(customerCode, text);
        if (StringUtils.isBlank(result)) {
            log.error("敏感信息解密失败, customerCode={}, text={}", customerCode, text);
            //throw new ServiceException(CodeMsg.SYSTEM_SENSITIVE_DECRYPT_FAIL);
        }

        return result;
    }

    /**
     * 获取加密字段名称列表
     *
     * @param obj
     * @return
     * @throws Exception
     */
    private Set<String> getEncryptFieldNames(Object obj) throws Exception {
        Set<String> encryptFields = new HashSet<>();
        Class<?> clazz = obj.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Encryption encryption = clazz.getAnnotation(Encryption.class);
            if (encryption != null) {
                encryptFields.addAll(Arrays.asList(encryption.value()));
            }
        }
        return encryptFields;
    }

    /**
     * 获取加密字段值以及customerCode和customerId
     *
     * @param obj
     * @param encryptFields
     * @return
     * @throws Exception
     */
    private Map<String, String> getEncryptFieldValues(Object obj, Set<String> encryptFields) throws Exception {

        Map<String, String> resultMap = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                String fieldName = field.getName();
                field.setAccessible(true);
                if (encryptFields.contains(fieldName) && !resultMap.containsKey(fieldName)) {
                    String fieldValue = (String) field.get(obj);
                    if (StringUtils.isNotBlank(fieldValue)) {
                        resultMap.put(fieldName, fieldValue);
                    }
                }
            }
        }

        return resultMap;
    }

    /**
     * 将map中的数据脱敏返回
     *
     * @param fieldValueMap
     * @return
     */
    private String desensitizationMapValues(Map<String, String> fieldValueMap) {

        if (CollectionUtils.isEmpty(fieldValueMap)) {
            return null;
        }
        Set<String> keys = fieldValueMap.keySet();
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            sb.append(key).append("=").append(DesensitizationUtils.commonEncrypt(fieldValueMap.get(key))).append(", ");
        }
        return sb.toString();
    }

    /**
     * 根据秘钥加密字符串
     *
     * @param text
     * @param key
     * @return
     */
    private String encryptByKey(String text, String key) throws Exception {
        if (StringUtils.isAnyBlank(text, key)) {
            log.error("根据秘钥加密字符串失败，加密字符串或者秘钥为空， text={}, key={}", DesensitizationUtils.commonEncrypt(text), DesensitizationUtils.commonEncrypt(key));
            throw new Exception("加密字符串或者秘钥为空");
        }
        String result = AESUtils.encrypt(text, key);
        if (StringUtils.isBlank(result)) {
            log.error("根据秘钥加密字符串失败，加密后的字符串为空，text={}, key={}", DesensitizationUtils.commonEncrypt(text), DesensitizationUtils.commonEncrypt(key));
            throw new Exception("加密后的字符串为空");
        }
        return result;
    }

    /**
     * 根据秘钥解密字符串
     *
     * @param text
     * @param key
     * @return
     */
    private String decryptByKey(String text, String key) throws Exception {
        if (StringUtils.isAnyBlank(text, key)) {
            throw new Exception("解密字符串为空或者秘钥为空");
        }
        String result = AESUtils.decrypt(text, key);
        if (StringUtils.isBlank(result)) {
            throw new Exception("解密后的字符串为空");
        }
        return result;
    }

    /**
     * 是否跳过检查该对象的字段是否需要加解密
     * 判断对象是否是8中基本数据类型或者字符串类型
     *
     * @param obj
     * @return
     */
    private boolean isSkipCheckField(Object obj) {

        if (obj == null) {
            return true;
        }

        // 8中基本数据类型或者字符串都可以直接跳过该对象的加解密
        if (obj instanceof Character
                || obj instanceof Boolean
                || obj instanceof Byte
                || obj instanceof Short
                || obj instanceof Integer
                || obj instanceof Long
                || obj instanceof Float
                || obj instanceof Double
                || obj instanceof String
                ) {
            return true;
        }
        return false;
    }
}

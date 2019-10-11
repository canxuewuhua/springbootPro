package com.example.demo.aop;

import com.example.demo.common.service.SensitiveService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 敏感信息加解密
 * 一般的数据都是以客户的秘钥进行加解密
 * 但是到账表比较特殊，线下到账时还不知道客户信息，所以没法使用客户的秘钥进行加解密
 * 所以到账表的线下还款使用商户的秘钥进行加解密，线上还款的使用客户的秘钥进行加解密
 */
@Aspect
@Component
@Slf4j
public class SensitiveAop {


    @Autowired
    private SensitiveService sensitiveService;

    /**
     * 定义切点，对DAO层进行拦截
     */
    @Pointcut("execution(* com.example.demo.mapper.*.*(..))")
    public void privilege() {
    }

    /**
     * 加解密
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around("privilege()")
    public Object isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        // 对参数进行敏感信息加密
        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            if (obj instanceof List){
                List listResult = (List) obj;
                for (Object objList : listResult) {
                    checkAndEncrypt(objList);
                }
            }else {
                checkAndEncrypt(obj);
            }
        }

        // 对返回数据中的敏感信息解密
        Object result = joinPoint.proceed();
        if (result instanceof List) {
            List listResult = (List) result;
            for (Object obj : listResult) {
                checkAndDecrypt(obj);
            }
        } else {
            checkAndDecrypt(result);
        }

        // 因为对参数进行了加密，所有为了不破坏参数的原始数据，该处应该对参数进行解密
        for (Object arg : args) {
            if (arg instanceof List){
                List listResult = (List) arg;
                for (Object objList : listResult) {
                    checkAndDecrypt(objList);
                }
            }else {
                checkAndDecrypt(arg);
            }
        }
        return result;
    }

    /**
     * 对敏感信息进行加密
     *
     * @param arg
     */
    private void checkAndEncrypt(Object arg) throws Exception {
        // 根据客户的秘钥加密
        sensitiveService.encryptObject(arg, true);
    }

    /**
     * 对敏感信息进行解密
     *
     * @param result
     */
    private void checkAndDecrypt(Object result) throws Exception {
        sensitiveService.decryptObject(result, true);
    }
}

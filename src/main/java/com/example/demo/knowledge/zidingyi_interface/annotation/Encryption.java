package com.example.demo.knowledge.zidingyi_interface.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段加解密注解，默认是以客户秘钥进行加解密的
 * 需要注意的是：如果没有特殊要求，使用该注解类的实例的customerId或者customerCode值不能都为空，否则拦截器会抛出异常
 * 该类一般使用在DAO层的入参和返回值(主要是VO或者DTO)上
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Encryption {
    /**
     * 需要加解密的字段列表
     *
     * @return
     */
    String[] value();
}

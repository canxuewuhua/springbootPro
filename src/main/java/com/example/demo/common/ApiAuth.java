package com.example.demo.common;

import java.lang.annotation.*;

/**
 * 验签注解，主要使用在Controller上，可以加在类和方法上
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiAuth {

    String value() default "DEFAULT";

    boolean authSwitch() default true;
}

package com.example.demo.service.annotation;

import java.lang.annotation.*;

/**
 * 创建自定义注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyFirstAnnotation {
	String value() default "";
}

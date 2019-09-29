package com.example.demo.exercise.delete_if_else.strategy;

import java.lang.annotation.*;

/**
 * @author yongqiang.zhu
 * 自定义注解
 * 用于标识该处理器对应哪个订单类型
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {
	String value();
}

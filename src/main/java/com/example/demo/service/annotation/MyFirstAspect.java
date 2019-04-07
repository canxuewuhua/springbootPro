package com.example.demo.service.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 定义切面类
 *
 * 创建完自定义注解后，很显然的思路是如何让注解起作用。这里以输出日志的注解为例，当用自定义注解来修饰方法时，我们期望在方法执*行的前后输出日志记录，那么我们必须采用AOP（面向切面编程）的思想，理所当然地，我们需要定义切面类
 */

@Aspect
@Component
public class MyFirstAspect {

	@Pointcut("@annotation(MyFirstAnnotation)")
	public void annotationPointcut() {
	}

	@Before("annotationPointcut()")
	public void beforePointcut(JoinPoint joinPoint) {
		MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		MyFirstAnnotation annotation = method.getAnnotation(MyFirstAnnotation.class);
		String value = annotation.value();
		System.out.println("准备"+value);
	}

	@After("annotationPointcut()")
	public void afterPointcut(JoinPoint joinPoint) {
		MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		MyFirstAnnotation annotation = method.getAnnotation(MyFirstAnnotation.class);
		String value = annotation.value();
		System.out.println("结束"+value);
	}
}

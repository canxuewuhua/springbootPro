package com.example.demo.service.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 定义日志切面类
 *
 * 创建完自定义注解后，很显然的思路是如何让注解起作用。这里以输出日志的注解为例，当用自定义注解来修饰方法时，我们期望在方法执*行的前后输出日志记录，那么我们必须采用AOP（面向切面编程）的思想，理所当然地，我们需要定义切面类
 *
 * 在类上使用 @Aspect 注解 使之成为切面类
 * 在类上使用 @Component 注解 把切面类加入到IOC容器中
 *
 * 注意：要导入spring boot支持aop的依赖，否则切面就不起作用
 *
 */

@Aspect
@Component
public class MyFirstAspect {

	@Pointcut("@annotation(MyFirstAnnotation)")
	public void annotationPointcut() {
	}

	/**
	 * 前置通知：目标方法执行之前执行以下方法体的内容
	 * @param joinPoint
	 */
	@Before("annotationPointcut()")
	public void beforePointcut(JoinPoint joinPoint) {
		MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		MyFirstAnnotation annotation = method.getAnnotation(MyFirstAnnotation.class);
		String value = annotation.value();
		System.out.println("准备"+value);
	}

	/**
	 * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
	 * @return
	 */
	@Around("annotationPointcut()")
	public Object advice(ProceedingJoinPoint joinPoint){
		System.out.println("通知之开始");
		Object retmsg=null;
		try {
			retmsg=joinPoint.proceed();
			System.err.println("++++++++"+retmsg);
		}catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("通知之结束");
		return retmsg;
	}


	/**
	 * 后置通知：目标方法执行之后执行以下方法体的内容，不管是否发生异常
	 * @param joinPoint
	 */
	@After("annotationPointcut()")
	public void afterPointcut(JoinPoint joinPoint) {
		MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		MyFirstAnnotation annotation = method.getAnnotation(MyFirstAnnotation.class);
		String value = annotation.value();
		System.out.println("结束"+value);
	}
}

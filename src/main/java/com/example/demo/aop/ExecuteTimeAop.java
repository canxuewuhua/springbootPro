package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 统计controller方法的执行时间 ，如果超过1000ms则打印日志
 */
@Component
@Aspect
@ComponentScan
@EnableAspectJAutoProxy
@Slf4j
public class ExecuteTimeAop {

    private static Long executeTimeMillis =1000L;

    private Map<Long, Map<String, List<Long>>> threadMap = new ConcurrentHashMap<>(200);

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void controller() {
    }

    @Before(value = "controller()")
    public void before(JoinPoint joinPoint) {
        Map<String, List<Long>> methodTimeMap = threadMap.get(Thread.currentThread().getId());
        List<Long> list;
        if (methodTimeMap == null) {
            methodTimeMap = new HashMap<>();
            list = new LinkedList<>();
            list.add(System.currentTimeMillis());
            methodTimeMap.put(joinPoint.toShortString(), list);
            threadMap.put(Thread.currentThread().getId(), methodTimeMap);
        } else {
            list = methodTimeMap.get(joinPoint.toShortString());
            if (list == null) list = new LinkedList<>();
            list.add(System.currentTimeMillis());
            methodTimeMap.put(joinPoint.toShortString(), list);
        }
    }

    @After(value = "controller()")
    public void after(JoinPoint joinPoint) {
        Map<String, List<Long>> methodTimeMap = threadMap.get(Thread.currentThread().getId());
        List<Long> list = methodTimeMap.get(joinPoint.toShortString());
        if (System.currentTimeMillis() - list.get(list.size() - 1) > executeTimeMillis) {
            log.error("方法 {} 耗时 {} 毫秒，请注意优化", joinPoint.toShortString(), System.currentTimeMillis() - list.get(list.size() - 1));
        }

        list.remove(list.size() - 1);
    }
}

package com.example.demo.study.proxy.dynamic.cglib;

import com.example.demo.study.proxy.dynamic.jdk.AirPlane;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Enhancer;

public class CglibProxy implements MethodInterceptor {

    AirPlane airPlane;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("method "+methodInvocation.getMethod()+" is called on "+
                methodInvocation.getThis()+" with args "+methodInvocation.getArguments());
        Object ret=methodInvocation.proceed();
        System.out.println("method "+methodInvocation.getMethod()+" returns "+ret);
        return ret;

    }

    public AirPlane getProxy(final AirPlane air){
        this.airPlane = air;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(airPlane.getClass());
//        enhancer.setCallback(this);
        return (AirPlane)enhancer.create();
    }
}

package com.example.demo.study.proxy.dynamic.jdk;

/**
 * 目标类
 */
public class CsAir implements AirPlane{
    @Override
    public String buyTicket(String name) {
        // 出票
        System.out.println("中国南方航空欢迎你");
        return name + "，南方航空，NO:"+ Math.random();
    }
}

package com.example.demo.study.proxy.stat;

public class CsAir implements AirPlane {
    @Override
    public String buyTicket(String name) {
        // 出票
        return name + "，静态代理，中国南航 NO:"+ Math.random();
    }
}

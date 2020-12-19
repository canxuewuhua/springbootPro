package com.example.demo.study.proxy.stat;

public class EastPalne implements AirPlane{
    @Override
    public String buyTicket(String name) {
        // 出票
        return name + "，静态代理，中国东方航空 NO:"+ Math.random();
    }
}

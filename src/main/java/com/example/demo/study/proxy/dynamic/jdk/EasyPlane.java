package com.example.demo.study.proxy.dynamic.jdk;

public class EasyPlane implements AirPlane{
    @Override
    public String buyTicket(String name) {
        // 出票
        return name + "，东方航空，NO:"+ Math.random();
    }
}

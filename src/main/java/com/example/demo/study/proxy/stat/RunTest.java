package com.example.demo.study.proxy.stat;

/**
 * 其实该stat下的实例是使用  工厂模式实现的
 */
public class RunTest {

    public static void main(String[] args) {
        AirPlane airPlane = new Fliggy();

        System.out.println(airPlane.buyTicket("JackMa"));;
    }
}

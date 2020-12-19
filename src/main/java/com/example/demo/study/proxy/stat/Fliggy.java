package com.example.demo.study.proxy.stat;

/**
 * 代理类 Fliggy
 * 只需在代理类中   ---切换----
 *
 * 各自实现类只需管自己的业务就行了
 *
 * 接口AirPlane的方法定了之后就按照这样的模板就行了  比如定义了若干方法
 */
public class Fliggy implements AirPlane{

    AirPlane csAir;

    public Fliggy(){
        // 实例化对象 在开发的时候讲飞猪写好
        csAir = new CsAir();
//        csAir = new EastPalne();
    }


    @Override
    public String buyTicket(String name) {
        return csAir.buyTicket(name);
    }
}

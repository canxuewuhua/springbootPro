package com.example.demo.study.proxy.dynamic.jdk;


/**
 *  aop 切面 借助InvocationHandler   控制目标类的调用时机
 *  目标类在调用前后 做一些事情
 *
 *  通过上面的讲解和示例动态代理的原理及使用方法，在Spring中的两大核心IOC和AOP中的AOP(面向切面编程)的思想就是动态代理，在代理类的前面和后面加上不同的切面组成面向切面编程。
 */
public class RunTest {

    public static void main(String[] args) {
        //要代理的真实对象
        CsAir csAir = new CsAir();

        //代理对象的调用处理程序，我们将要代理的真实对象传入代理对象的调用处理的构造函数中，最终代理对象的调用处理程序会调用真实对象的方法
        JdkProxy jdkProxy = new JdkProxy();

        AirPlane airPlane = jdkProxy.getProxy(csAir);

        String res = airPlane.buyTicket("Smith");

        System.out.println(res);
    }
}

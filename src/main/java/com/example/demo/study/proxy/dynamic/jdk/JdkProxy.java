package com.example.demo.study.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  JDK动态代理类
 *
 *  运行时期生成的
 *
 *  InvocationHandler这个东西相当于一个回调
 */
public class JdkProxy implements InvocationHandler {


    private AirPlane airPlane;

    /**
     * 通过Proxy类的newProxyInstance方法创建代理对象，我们来看下方法中的参数
     * 第一个参数：airPlane.getClass().getClassLoader()，使用handler对象的classloader对象来加载我们的代理对象
     * 第二个参数：airPlane.getClass().getInterfaces()，这里为代理类提供的接口是真实对象实现的接口，这样代理对象就能像真实对象一样调用接口中的所有方法
     * 第三个参数：handler，我们将代理对象关联到上面的InvocationHandler对象上
     */
    public AirPlane getProxy(AirPlane airPlane){
        this.airPlane = airPlane;

        // 使用Java 反射机制
        // 运行期间创建代理对象
        // 参数 类加载器 目标类哪些接口 this对象
        return (AirPlane) Proxy.newProxyInstance(airPlane.getClass().getClassLoader(),airPlane.getClass().getInterfaces(), this);
    }

    /**
     * System.out.println("买票之前记录购票信息");
     * Object res = method.invoke(airPlane, args);
     * System.out.println("买票之后记录购票信息");
     * -----------------为什么输出是？-----------------
     * 买票之前记录购票信息
     * 买票之后记录购票信息
     * Smith，南方航空，NO:0.05230286633757586
     *
     * 解释： System.out.println(res); 先执行的是invoke方法，后打印的main方法中res
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买票之前记录购票信息");
        Object res = method.invoke(airPlane, args);

        System.out.println("买票之后记录购票信息");

        return res;
//        return "我不干了";
    }
}

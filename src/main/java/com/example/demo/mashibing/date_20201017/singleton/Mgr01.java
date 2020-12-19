package com.example.demo.mashibing.date_20201017.singleton;

/**
 * 饿汉式
 *
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用 推荐使用
 * 唯一缺点 不管用到与否 类加载就完成实例化
 * Class.forName("")
 * 话说你不用用的，你装载它干啥
 *
 * 饿汉式 是你不管自己饿不饿 先初始化一个馒头放那
 */
public class Mgr01 {

    private static final Mgr01 INSTANCE = new Mgr01();

    private Mgr01(){}

    public static Mgr01 getInstance(){
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        // 只有一个实例对象  JVM保证只有一个
        Mgr01 m1 = Mgr01.getInstance();
        Mgr01 m2 = Mgr01.getInstance();
        System.out.println(m1 == m2);
    }
}

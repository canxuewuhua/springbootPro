package com.example.demo.mashibing.date_20201017.singleton;

/**
 *  懒汉式
 *  虽然达到了按需初始化的目的 但却带来了线程不安全的问题
 */
public class Mgr03 {

    private static Mgr03 INSTANCE;

    private Mgr03(){}

    /**
     * 要用的时候才去初始化 但是线程不安全 多个线程过来肯定有问题
     * 如果加上synchronized应该就没问题了吧  但是这个地方锁的粒度有点大，因为方法中可能还与其他逻辑呢
     * @return
     */
    public static synchronized Mgr03 getInstance(){
        if (INSTANCE == null){
            try{
                Thread.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->
                    System.out.println(Mgr03.getInstance().hashCode())
            ).start();
        }
    }
}

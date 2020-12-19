package com.example.demo.mashibing.date_20201017.singleton;

/**
 *  懒汉式
 *  虽然达到了按需初始化的目的 但却带来了线程不安全的问题
 */
public class Mgr02 {

    private static Mgr02 INSTANCE;

    private Mgr02(){}

    /**
     * 要用的时候才去初始化 但是线程不安全 多个线程过来肯定有问题
     * @return
     */
    public static Mgr02 getInstance(){
        if (INSTANCE == null){
            try{
                Thread.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            INSTANCE = new Mgr02();
        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->
                    System.out.println(Mgr02.getInstance().hashCode())
            ).start();
        }
    }
}

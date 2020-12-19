package com.example.demo.mashibing.date_20201017.singleton;

/**
 *  懒汉式
 *  虽然达到了按需初始化的目的 但却带来了线程不安全的问题
 */
public class Mgr04 {

    private static Mgr04 INSTANCE;

    private Mgr04(){}

    /**
     * 要用的时候才去初始化 但是线程不安全 多个线程过来肯定有问题
     * 如果加上synchronized应该就没问题了吧
     * @return
     */
    public static Mgr04 getInstance(){
        if (INSTANCE == null){
            // 妄图通过减小同步代码块的方式提高效率，然后不可行

            // 比如第一个线程进来然后一直走 进行初始化
            // 假如第一个线程还没有初始化完，第二个线程进来了判断为空，此时加锁的线程一初始化完了，锁也释放了，线程二，拿到锁，又进行了一遍初始化 有线程问题，引入双重检查吧
            synchronized (Mgr04.class){
                try{
                    Thread.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                INSTANCE = new Mgr04();
            }
        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->
                    System.out.println(Mgr04.getInstance().hashCode())
            ).start();
        }
    }
}

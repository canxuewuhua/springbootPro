package com.example.demo.mashibing.date_20201017.singleton;

/**
 *  懒汉式
 *  虽然达到了按需初始化的目的 但却带来了线程不安全的问题
 */
public class Mgr05 {

    /**
     * 此处需要加上volatile  因为要防止指令重排序  -----单例模式创建对象  完结
     */
    private static volatile Mgr05 INSTANCE;

    private Mgr05(){}

    /**
     * 要用的时候才去初始化 但是线程不安全 多个线程过来肯定有问题
     * 如果加上synchronized应该就没问题了吧
     * @return
     */
    public static Mgr05 getInstance(){
        if (INSTANCE == null){
            // 妄图通过减小同步代码块的方式提高效率，然后不可行

            // 比如第一个线程进来然后一直走 进行初始化
            // 假如第一个线程还没有初始化完，第二个线程进来了判断为空，此时加锁的线程一初始化完了，锁也释放了，线程二，拿到锁，又进行了一遍初始化有线程问题，引入双重检查吧
            // 双重检查锁，在synchronized里面再来一次判空，因为线程一释放了锁就等于已经初始化完了，此时INSTANCE不会为空

            // 总结：双重检查可保证对象初始化时的线程的安全
            synchronized (Mgr05.class){
                // 双重检查
                if (INSTANCE == null){
                    try{
                        Thread.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr05();
                }
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
                    System.out.println(Mgr05.getInstance().hashCode())
            ).start();
        }
    }
}

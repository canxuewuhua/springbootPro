package com.example.demo.mashibing.date_20201017.chongxie;

public class Cat extends Animals{
    synchronized void shout(){
        /**
         * 如果在这个加一个Synchronized
         * 父类这个方法也有
         * 因为父类这个方法加锁了，子类中也加锁了，如果Synchronized不是可重入锁 就会造成死锁的问题
         *
         * 所以Synchronized是可重入锁
         * 子类在加锁的时候，此时又调用了super.shout()方法，该方法也加锁了
         * 没有影响
         */
        super.shout();
        System.out.println("喵喵喵。。。");
    }
}

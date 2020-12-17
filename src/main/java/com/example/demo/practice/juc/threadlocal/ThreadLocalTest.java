package com.example.demo.practice.threadlocal;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    public static void main(String[] args) {

        // 创建线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20,20,0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), threadFactory);

        // 此方法不会出现线程安全问题 如果使用DateUtilUnSafe就会报线程安全问题
        // 因为parse方法 他没有做安全处理，他不能保证原子性 多线程同时操作的时候，就会出现线程安全问题
        // 他这个问题是多个线程共享一个SimpleDateFormat造成的
        // 解决办法不让他线程共享 每个线程保存一份自己的SimpleDateFormat的对象
        // 独立保存一份自己的变量副本  每个线程使用自己的变量副本 这样就不会影响其他线程

        // ThreadLocalMap
        for (int i=0; i< 20;i++){
            threadPoolExecutor.execute(
                    ()->System.out.println(DateUtilSafe.parse("2020-12-01 23:02:15"))
            );
        }
        threadPoolExecutor.shutdown();
    }
}

package com.example.demo.test.singleton;

/**
 * @author yongqiang.zhu
 * 单例模式
 *  饥汉
 *  迫不"饥"待出生 new
 *
 *  但是这有潜在的性能问题，因为没有使用这个对象之前，就把它加载到内存中去是一种巨大的浪费，那就要改，去使用另外一种。
 */
public class SingletonOne {
	public static SingletonOne singletonOne = new SingletonOne();
	public  SingletonOne(){
	}
	public static SingletonOne getSingleton(){
		return singletonOne;
	}
}

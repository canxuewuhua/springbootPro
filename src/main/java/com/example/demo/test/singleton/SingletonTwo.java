package com.example.demo.test.singleton;

/**
 *  @author yongqiang.zhu
 *  懒汉
 *  懒的出生，初始化为null
 */
public class SingletonTwo {
	private static SingletonTwo singletonTwo = null;
	private SingletonTwo(){}
	public static SingletonTwo getSingleton(){
		if (singletonTwo == null){
			singletonTwo  = new SingletonTwo();
		}
		return singletonTwo;
	}
}

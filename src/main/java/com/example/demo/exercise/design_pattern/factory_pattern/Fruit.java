package com.example.demo.exercise.design_pattern.factory_pattern;

/**
 * @author yongqiang.zhu
 * 为水果类声明一个接口
 */
public interface Fruit {
	// 生长
	void grow();
	// 收获
	void harvest();
	// 种植
	void plant();
}

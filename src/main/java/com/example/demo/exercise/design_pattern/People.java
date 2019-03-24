package com.example.demo.exercise.design_pattern;

/**
 * @author yongqiang.zhu
 */
public class People {
	public static void main(String[] args) throws Exception {
		FruitGardener fg = new FruitGardener();
		Fruit ap = fg.factory("Apple");
		ap.grow();

		Fruit gp = fg.factory("Grape");
		gp.plant();

		Fruit dd = fg.factory("ddd");// 抛出Bad fruit reques异常
	}
}

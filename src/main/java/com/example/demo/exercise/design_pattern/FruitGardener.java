package com.example.demo.exercise.design_pattern;

/**
 * @author yongqiang.zhu
 */
public class FruitGardener {
	public static Fruit factory(String which) throws Exception{
		if (which.equalsIgnoreCase("apple")){
			return new Apple();
		}
		else if (which.equalsIgnoreCase("strawberry")){
			return new Strawberry();
		}
		else if (which.equalsIgnoreCase("grape")){
			return new Grape();
		}else{
			throw new Exception("Bad fruit request");
		}
	}
}

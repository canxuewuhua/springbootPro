package com.example.demo.test.test_demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yongqiang.zhu
 * @date 2020/5/19 22:41
 */
public class ProtectTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
//		map.put("name", "zhangsan");
//		map.put("address", "zhengzhou");

		for (Map.Entry<String,String> entry : map.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		System.out.println("dfdsfdsafsd");
	}
}

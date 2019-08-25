package com.example.demo.exercise.listmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yongqiang.zhu
 * @date 2019/8/25 23:09
 */
public class Test {
	public static void main(String[] args) {
		List<Map<String,String>> mapList = new ArrayList<>();
		Map<String,String> map = new HashMap<>();
		map.put("proCode","proOne");
		map.put("count","12");
		mapList.add(map);

		Map<String,String> map1 = new HashMap<>();
		map1.put("proCode","proTwo");
		map1.put("count","12");
		mapList.add(map1);


		System.out.println(mapList);
	}
}

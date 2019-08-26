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
		map.put("checkDate","2018-06-01");
		map.put("count","12");
		mapList.add(map);

		Map<String,String> map1 = new HashMap<>();
		map1.put("proCode","proOne");
		map.put("checkDate","2018-06-02");
		map1.put("count","23");
		mapList.add(map1);

		List<Map<String,String>> mapList2 = new ArrayList<>();
		Map<String,String> map3 = new HashMap<>();
		map3.put("proCode","proTwo");
		map3.put("checkDate","2018-06-01");
		map3.put("count","15");
		mapList2.add(map3);

		Map<String,String> map4= new HashMap<>();
		map4.put("proCode","proTwo");
		map4.put("checkDate","2018-06-02");
		map4.put("count","28");
		mapList2.add(map4);

		mapList.addAll(mapList2);





		System.out.println(mapList);
	}
}

package com.example.demo.exercise.listmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yongqiang.zhu
 * @date 2019/9/8 10:09
 */
public class UseMapTest {

	public static void main(String[] args) {
		List<Map<String,String>> mapList = new ArrayList<>();
		Map<String,String> map = new HashMap<>();
		map.put("user_id","1027341854");
		map.put("trate_date","2019-04-26 10:00:37.254");
		Map<String,String> map2 = new HashMap<>();
		map2.put("user_id","1027341854");
		map2.put("trate_date","2019-04-26 10:05:37.254");
		mapList.add(map);
		mapList.add(map2);



		Map<String,String> map3 = new HashMap<>();
		map3.put("user_id","1027341854");
		map3.put("user_id","1027341855");
		map3.put("trate_date","2019-04-26 10:05:37.254");
		mapList.add(map3);
		System.out.println(mapList);

//		if (mapList.contains(map3)){
//			System.out.println("mapList中包含map3一样的map");
//		}else{
//			System.out.println("mapList中不包含map3一样的map");
//		}
//		for (Map<String,String> map1 : mapList){
//			if (map1.containsKey("1027341854")){
//				System.out.println("mapList中包含和user_id=1027341854相同的map");
//			}else{
//				System.out.println("mapList中不包含和user_id=1027341854相同的map");
//			}
//		}

	}
}

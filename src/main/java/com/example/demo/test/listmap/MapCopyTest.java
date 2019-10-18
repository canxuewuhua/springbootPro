package com.example.demo.test.listmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yongqiang.zhu
 * @date 2019/10/18 22:08
 */
public class MapCopyTest {
	public static void main(String[] args) {
		Map<String,String> map =  new HashMap<>();
		Map<String,String> map1 = new HashMap<>();
		map.put("age", "25");
		// 实现浅拷贝方式一：“=”
		map1 = map;
		map.remove("age");
		System.out.println(map);
		System.out.println(map1);

		Map<String,String> map2 =  new HashMap<>();
		Map<String,String> map3 =  new HashMap<>();
		map2.put("address", "北京市");
		map3.putAll(map2);
		map2.remove("address");
		System.out.println(map2);
		System.out.println(map3);

		HashMap<String,String>  paramMap = new HashMap<>();
		paramMap.put("name", "Jordon");
		HashMap<String,String>  paramMap2 = new HashMap<>();
		paramMap2.putAll(paramMap);
		paramMap.remove("name");
		System.out.println(paramMap);
		System.out.println(paramMap2);

	}
}

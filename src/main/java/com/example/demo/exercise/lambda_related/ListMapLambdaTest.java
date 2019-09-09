package com.example.demo.exercise.lambda_related;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yongqiang.zhu
 * @date 2019/9/9 22:56
 */
public class ListMapLambdaTest {

	public static void main(String[] args) {
		printGroupByMoreElements();
	}

	/**
	 * 根据日期分组，根据**计数
	 */
	public static void printGroupByMoreElements() {

		List<FacePlusPlusDTO> faceList= Arrays.asList(
				new FacePlusPlusDTO("2019-09-08",1567908544L,"1027341851"),
				new FacePlusPlusDTO("2019-09-09",1567955344L,"1027341851"),
				new FacePlusPlusDTO("2019-09-10",1567955344L,"1027341852"),
				new FacePlusPlusDTO("2019-09-10",1567955344L,"1027341851"),
				new FacePlusPlusDTO("2019-09-11",1567956544L,"1027341852"));

		Map<String, Long> caseTotal = faceList.stream()
				.sorted(Comparator.comparing(FacePlusPlusDTO::getTransDate))
				.collect(Collectors.groupingBy(FacePlusPlusDTO::getTransDate, Collectors.counting()));
		System.out.println(caseTotal);
		List<Map<String,Object>> mapList = new ArrayList<>();
		for (Map.Entry<String, Long> entry : caseTotal.entrySet()) {
			String transDate = entry.getKey();
			long count = entry.getValue();
			Map<String,Object> map = new HashMap<>();
			map.put(transDate, count);
			mapList.add(map);
		}
		System.out.println(mapList);






	}
}

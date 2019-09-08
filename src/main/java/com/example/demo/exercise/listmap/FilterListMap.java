package com.example.demo.exercise.listmap;

import com.example.demo.util.DateUtil;

import java.util.*;

/**
 * @author yongqiang.zhu
 * @date 2019/9/8 2:22
 */
public class FilterListMap {

	public static void main(String[] args) {
		List<Map<String,Object>> mapList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("user_id","1027341850");
		map.put("trate_date",DateUtil.parseDate("2019-04-26 10:00:37.254", DateUtil.FORMAT_PATTERN_TIME2));
		Map<String,Object> map2 = new HashMap<>();
		map2.put("user_id","1027341851");
		map2.put("trate_date",DateUtil.parseDate("2019-04-26 10:00:37.254", DateUtil.FORMAT_PATTERN_TIME2));
		Map<String,Object> map3 = new HashMap<>();
		map3.put("user_id","1027341850");
		map3.put("trate_date",DateUtil.parseDate("2019-04-26 10:02:37.254", DateUtil.FORMAT_PATTERN_TIME2));
		Map<String,Object> map4 = new HashMap<>();
		map4.put("user_id","1027341851");
		map4.put("trate_date",DateUtil.parseDate("2019-04-26 10:03:37.254", DateUtil.FORMAT_PATTERN_TIME2));
		Map<String,Object> map5 = new HashMap<>();
		map5.put("user_id","1027341850");
		map5.put("trate_date",DateUtil.parseDate("2019-04-26 10:06:37.254", DateUtil.FORMAT_PATTERN_TIME2));
		Map<String,Object> map6 = new HashMap<>();
		map6.put("user_id","1027341850");
		map6.put("trate_date",DateUtil.parseDate("2019-04-26 10:07:47.254", DateUtil.FORMAT_PATTERN_TIME2));
		Map<String,Object> map7 = new HashMap<>();
		map7.put("user_id","1027341851");
		map7.put("trate_date",DateUtil.parseDate("2019-04-26 10:07:57.254", DateUtil.FORMAT_PATTERN_TIME2));
		Map<String,Object> map8 = new HashMap<>();
		map8.put("user_id","1027341851");
		map8.put("trate_date",DateUtil.parseDate("2019-04-26 10:11:37.258", DateUtil.FORMAT_PATTERN_TIME2));
		mapList.add(map);
		mapList.add(map2);
		mapList.add(map3);
		mapList.add(map4);
		mapList.add(map5);
		mapList.add(map6);
		mapList.add(map7);
		mapList.add(map8);
		for (Map<String,Object> printMap1: mapList){
			System.out.print(printMap1.get("user_id")+"-"+DateUtil.formatDate((Date)printMap1.get("trate_date"),DateUtil.FORMAT_PATTERN_TIME2)+";");
		}
		System.out.println("*******************");
		List<Map<String, Object>> filteredListMap = filterListMap(mapList);
		for (Map<String,Object> printMap2: filteredListMap){
			System.out.print(printMap2.get("user_id")+"-"+DateUtil.formatDate((Date)printMap2.get("trate_date"),DateUtil.FORMAT_PATTERN_TIME2)+";");
		}

	}

	public static List<Map<String, Object>> filterListMap(List<Map<String, Object>> mapList){
		// map中包含的元素：user_id， create_time
		// 首先list必须进行排序，且按create_time正序排序
		Date benchmarkTime = (Date)mapList.get(0).get("trate_date");
		//** filteredListMap中只有用户信息
		// 定义一个过滤后listMap
		List<Map<String, Object>> filteredListMap = new ArrayList<Map<String, Object>>();
		// 定义一个五分钟listMap
		Map<String, Object> fiveMinutesMap = new HashMap<String, Object>();
		filteredListMap.add(mapList.get(0));
		fiveMinutesMap.put((String)mapList.get(0).get("user_id"), "1");
		for (int i = 1; i < mapList.size(); i++) {
			// 查询出当前map中的时间
			Date createTime = (Date)mapList.get(i).get("trate_date");
			// 拿createTime和基准时间进行对比
			// 时间间隔
			long timeInterval = Math.abs(DateUtil.getMillisecond(benchmarkTime, createTime));
			if (timeInterval-300000l <= 0){

				// 此时时间是小于五分钟内，看用户id是否重复
				// 重复的用户id不计入过滤后的listMap
				// 比如说1分，2分，3分都有同一个人
				// 时间在5分钟内，判断在5分钟内是否在重复的userId

				if (fiveMinutesMap.containsKey(mapList.get(i).get("user_id"))){
					continue;
				}else{
					// 五分钟map中不包含当前user_id，此时需将该user_id存入到五分钟map中
					// 同时将map存入到过滤后的listMap中
					fiveMinutesMap.put((String)mapList.get(i).get("user_id"), "1");
					filteredListMap.add(mapList.get(i));
				}
			}else{
				// createTime大于了基准时间，此时需调整基准时间
				benchmarkTime = (Date)mapList.get(i).get("trate_date");
				filteredListMap.add(mapList.get(i));
				fiveMinutesMap.clear();
				fiveMinutesMap.put((String)mapList.get(i).get("user_id"), "1");
			}
		}








		return filteredListMap;
	}
}

package com.example.demo.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yongqiang.zhu
 * @date 2019/5/2 13:09
 * 获取在list<String>中，这个值的索引位置
 */
public class TestIndexOf {
	public static void main(String[] args) {

		print();
	}

	public static void print(){
		List<City> cityList = new ArrayList<City>();
		City city = new City();
		city.setCityID("89757");
		city.setCityName("郑州市");
		cityList.add(city);
		List<String> cityNames = new ArrayList<>();
		cityNames.add("郑州市");
		cityNames.add("北京市");
		cityNames.add("南京市");
		int count = cityNames.indexOf(city.getCityName());
		System.out.println(count);
	}


}
@Data
class City{
	private String cityID;
	private String cityName;
}

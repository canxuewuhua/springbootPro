package com.example.demo.exercise.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author yongqiang.zhu
 * @date 2020/5/5 20:29
 */
public class JsonTest {
	public static void main(String[] args) {
		AreaCodeAndNameDTO areaCodeAndNameDTO = new AreaCodeAndNameDTO();
		ProvinceDTO provinceDTO = new ProvinceDTO();
		provinceDTO.setCode("10110");
		provinceDTO.setName("北京市");
		CityDTO cityDTO = new CityDTO();
		cityDTO.setCode("10110");
		cityDTO.setName("北京市");
		CountyDTO countyDTO = new CountyDTO();
		countyDTO.setCode("10111");
		countyDTO.setName("海淀区");
		areaCodeAndNameDTO.setProvince(provinceDTO);
		areaCodeAndNameDTO.setCity(cityDTO);
		areaCodeAndNameDTO.setCounty(countyDTO);
		System.out.println(JSONObject.toJSON(areaCodeAndNameDTO));
	}
}

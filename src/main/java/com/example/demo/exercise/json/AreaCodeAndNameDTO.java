package com.example.demo.exercise.json;

import lombok.Data;

/**
 * @author yongqiang.zhu
 * @date 2020/5/5 20:27
 */
@Data
public class AreaCodeAndNameDTO {
	private ProvinceDTO province;
	private CityDTO city;
	private CountyDTO county;
}
@Data
class ProvinceDTO {
	private String code;
	private String name;
}
@Data
class CityDTO {
	private String code;
	private String name;
}
@Data
class CountyDTO {
	private String code;
	private String name;
}
